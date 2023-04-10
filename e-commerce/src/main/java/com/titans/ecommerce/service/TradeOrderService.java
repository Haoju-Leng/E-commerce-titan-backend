package com.titans.ecommerce.service;

import com.titans.ecommerce.auth.AuthenticationService;
import com.titans.ecommerce.models.dto.ProductVO;
import com.titans.ecommerce.models.entity.Product;
import com.titans.ecommerce.models.entity.TradeOrder;
import com.titans.ecommerce.models.entity.User;
import com.titans.ecommerce.models.vo.TradeOrderVO;
import com.titans.ecommerce.repository.TradeOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TradeOrderService {
    @Autowired
    TradeOrderRepository tradeOrderRepository;

    @Autowired
    ProductService productService;

    @Autowired
    AuthenticationService authenticationService;

    private User getUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public List<TradeOrderVO> findBuyingOrdersByUser() {
        List<TradeOrder> tradeOrders = tradeOrderRepository.findOrdersByBuyerId(getUser().getId());

        if (tradeOrders == null)
            return Collections.emptyList();

        return tradeOrders.stream().map(this::convertOrderToOrderVO).toList();
    }

    public List<TradeOrderVO> findSellingOrdersByUser() {
        List<TradeOrder> tradeOrders = tradeOrderRepository.findOrdersBySellerId(getUser().getId());

        if (tradeOrders == null)
            return Collections.emptyList();

        return tradeOrders.stream().map(this::convertOrderToOrderVO).toList();
    }

    public TradeOrderVO findOrderProductId(Integer productId) {
        TradeOrder tradeOrder = tradeOrderRepository.findOrderByProductId(productId);
        if (tradeOrder == null)
            return null;

        return convertOrderToOrderVO(tradeOrder);
    }

    public TradeOrderVO createOrder(Integer productId, String deliveryMethod) {
        TradeOrder tradeOrder = new TradeOrder();
        ProductVO productVO = productService.getProductById(productId);
        // Update product state to inTransaction
        Product product = productService.getProductInfoById(productId);
        product.setState(Product.State.inTransaction);
        productService.productRepository.save(product);

        tradeOrder.setBuyerId(getUser().getId());
        tradeOrder.setState(TradeOrder.State.Processing);
        tradeOrder.setQuantity(productVO.getStock());
        tradeOrder.setProductId(productId);
        tradeOrder.setSellerId(productVO.getSellerId());
        tradeOrder.setUnitPrice(productVO.getPrice());
        tradeOrder.setDeliveryMethod(deliveryMethod);
        tradeOrder = tradeOrderRepository.save(tradeOrder);

        // Send notification email
        authenticationService.sendOrderCreatedNotification(productVO.getSellerId(), getUser().getId(), productVO.getName(), tradeOrder.getId());
        return convertOrderToOrderVO(tradeOrder);
    }

    public void deleteProcessingOrder(Integer orderId){
        tradeOrderRepository.deleteById(orderId);
    }

    public TradeOrderVO approveOrderBySeller(Integer productId) {
        TradeOrder item = tradeOrderRepository.findOrderByProductId(productId);
        item.setState(TradeOrder.State.Completed);
        Integer sellerId = item.getSellerId();
        Integer buyerId = item.getBuyerId();
        tradeOrderRepository.save(item);

        // Update product state to soldOut
        Product product = productService.getProductInfoById(productId);
        product.setState(Product.State.soldOut);
        productService.productRepository.save(product);

        // Send notification email
        authenticationService.sendOrderApprovalNotification(sellerId, buyerId, product.getName(), item.getId());

        return convertOrderToOrderVO(item);
    }

    public TradeOrderVO denyOrderBySeller(Integer productId) {
        TradeOrder item = tradeOrderRepository.findOrderByProductId(productId);
        item.setState(TradeOrder.State.Denied);
        Integer sellerId = item.getSellerId();
        Integer buyerId = item.getBuyerId();
        tradeOrderRepository.save(item);

        // Update product state to forSale
        Product product = productService.getProductInfoById(productId);
        product.setState(Product.State.forSale);
        productService.productRepository.save(product);

        // Send notification email
        authenticationService.sendOrderDeniallNotification(sellerId, buyerId, product.getName(), item.getId());
        return convertOrderToOrderVO(item);
    }

    TradeOrderVO convertOrderToOrderVO(TradeOrder tradeOrder){
        if (tradeOrder == null)
            return null;
        TradeOrder item = tradeOrderRepository.findOrderById(tradeOrder.getId());
        return TradeOrderVO
                .builder()
                .productId(item.getProductId())
                .sellerId(item.getSellerId())
                .state(item.getState())
                .unitPrice(item.getUnitPrice())
                .buyerId(item.getBuyerId())
                .quantity(item.getQuantity())
                .deliveryMethod(item.getDeliveryMethod())
                .build();
    }

}
