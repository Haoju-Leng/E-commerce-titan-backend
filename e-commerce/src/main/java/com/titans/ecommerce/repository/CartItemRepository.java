package com.titans.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.titans.ecommerce.models.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Integer>{
    @Query("SELECT * FROM cartitem WHERE cart_id=:{cartId}")
    List<CartItem> findByCartId(Integer cartId);
}
