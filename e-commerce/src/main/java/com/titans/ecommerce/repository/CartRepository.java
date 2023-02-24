package com.titans.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.titans.ecommerce.models.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer>{
    @Query("SELECT * FROM cart WHERE user_id=:{userId}")
    Cart findByUserId(Integer userId);
}
