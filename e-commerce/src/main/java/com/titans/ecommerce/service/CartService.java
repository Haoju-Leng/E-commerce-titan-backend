package com.titans.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.titans.ecommerce.models.entity.Cart;
import com.titans.ecommerce.models.entity.CartItem;
import com.titans.ecommerce.models.entity.User;
import com.titans.ecommerce.models.vo.CartItemVO;
import com.titans.ecommerce.models.vo.CartVO;
import com.titans.ecommerce.repository.CartItemRepository;
import com.titans.ecommerce.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    private User getUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public CartVO getCart() {
        return convertCartToCartVO(cartRepository.findByUserId(getUser().getId()));
    }

    CartVO convertCartToCartVO(Cart cart){
        CartVO cartVO=new CartVO();
        List<CartItemVO> items=cartItemRepository.findByCartId(cart.getId())
            .stream()
            .map(this::convertCartItemToCartItemVO)
            .toList();
        cartVO.setItems(items);
        cartVO.setTotalPrice(getTotalPrice(items));
        return cartVO;
    }

    CartItemVO convertCartItemToCartItemVO(CartItem cartItem){
        CartItemVO cartItemVO=new CartItemVO();
        
        return cartItemVO;
    }

    Double getTotalPrice(List<CartItemVO> items){
        return items
            .stream()
            .map(item->item.getProduct().getPrice()*item.getQuantity())
            .mapToDouble(Double::doubleValue)
            .sum();
    }
}
