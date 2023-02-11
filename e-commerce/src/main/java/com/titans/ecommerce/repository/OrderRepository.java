package com.titans.ecommerce.repository;

import com.titans.ecommerce.models.entity.TradeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<TradeOrder, Integer> {
}
