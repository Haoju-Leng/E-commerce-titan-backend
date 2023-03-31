package com.titans.ecommerce.repository;

import com.titans.ecommerce.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrdersBySellerId(Integer sellerId);

    List<Order> findOrdersByBuyerId(Integer buyerId);

    Order findOrderByOrderId(Integer orderId);
    Order findOrderByProductId(Integer productId);

}
