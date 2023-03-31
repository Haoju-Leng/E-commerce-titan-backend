package com.titans.ecommerce.service;

import com.titans.ecommerce.models.dto.ProductVO;
import com.titans.ecommerce.models.entity.Order;
import com.titans.ecommerce.models.entity.User;
import com.titans.ecommerce.models.vo.OrderVO;
import com.titans.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;


    private User getUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public List<OrderVO> findBuyingOrdersByUser() {
        List<Order> orders = orderRepository.findOrdersByBuyerId(getUser().getId());

        if (orders == null)
            return Collections.emptyList();

        return orders.stream().map(this::convertOrderToOrderVO).toList();
    }

    public List<OrderVO> findSellingOrdersByUser() {
        List<Order> orders = orderRepository.findOrdersBySellerId(getUser().getId());

        if (orders == null)
            return Collections.emptyList();

        return orders.stream().map(this::convertOrderToOrderVO).toList();
    }

    public OrderVO findOrderProductId(Integer productId) {
        Order order = orderRepository.findOrderByProductId(productId);
        if (order == null)
            return null;

        return convertOrderToOrderVO(order);
    }

    public OrderVO createOrder(Integer productId, String deliveryMethod) {
        Order order = new Order();
        ProductVO productVO = productService.getProductById(productId);
        order.setBuyerId(getUser().getId());
        order.setState(Order.State.Processing);
        order.setQuantity(productVO.getStock());
        order.setProductId(productId);
        order.setSellerId(productVO.getSellerId());
        order.setUnitPrice(productVO.getPrice());
        order.setDeliveryMethod(deliveryMethod);

        order = orderRepository.save(order);
        return convertOrderToOrderVO(order);
    }

    public void deleteProcessingOrder(Integer orderId){
        orderRepository.deleteById(orderId);
    }

    public OrderVO approveOrderBySeller(Integer orderId) {
        Order item = orderRepository.findOrderByOrderId(orderId);
        item.setState(Order.State.Completed);
        orderRepository.save(item);

        Integer productId = item.getProductId();
        productService.deleteProduct(productId);

        return convertOrderToOrderVO(item);
    }

    public OrderVO denyOrderBySeller(Integer orderId) {
        Order item = orderRepository.findOrderByOrderId(orderId);
        item.setState(Order.State.Denied);
        orderRepository.save(item);
        return convertOrderToOrderVO(item);
    }

    OrderVO convertOrderToOrderVO(Order order){
        if (order == null)
            return null;
        Order item = orderRepository.findOrderByOrderId(order.getId());
        return OrderVO
                .builder()
                .orderId(item.getOrderId())
                .productId(item.getProductId())
                .sellerId(item.getSellerId())
                .state(item.getState())
                .unitPrice(item.getUnitPrice())
                .buyerId(item.getBuyerId())
                .quantity(item.getQuantity())
                .build();
    }

}
