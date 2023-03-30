package com.titans.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.titans.ecommerce.models.entity.CartItem;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer>{

    List<CartItem> findCartItemsByCartId(Integer cartId);

    CartItem findCartItemByCartIdAndProductId(Integer cartId, Integer productId);
}
