package com.titans.ecommerce.repository;

import com.titans.ecommerce.models.entity.TradeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeOrderRepository extends JpaRepository<TradeOrder, Integer> {
    List<TradeOrder> findOrdersBySellerId(Integer sellerId);

    List<TradeOrder> findOrdersByBuyerId(Integer buyerId);

    TradeOrder findOrderByOrderId(Integer orderId);
    TradeOrder findOrderByProductId(Integer productId);

}
