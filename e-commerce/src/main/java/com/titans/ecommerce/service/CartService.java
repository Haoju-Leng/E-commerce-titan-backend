package com.titans.ecommerce.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.titans.ecommerce.models.dto.CartDTO;
import com.titans.ecommerce.models.dto.CartItemDTO;
import com.titans.ecommerce.models.dto.ProductVO;
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

    @Autowired
    ProductService productService;

    private User getUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public CartVO getCart() {
        return convertCartToCartVO(cartRepository.findByUserId(getUser().getId()));
    }

    public CartVO addCart(CartDTO cartDTO){
        Cart cart=new Cart();
        cart.setUser(getUser());
        cart=cartRepository.save(cart);
        final Integer cartId=cart.getId();
        cartDTO
            .getItems()
            .stream()
            .map(item->convertCartItemDTOToCartItem(item,cartId))
            .map(cartItemRepository::save)
            .toList();

        return convertCartToCartVO(cart);
    }

    public CartVO editCart(Integer id, CartDTO cartDTO){
        Cart cartInDB=cartRepository.findById(id).get();
        cartItemRepository.findByCartId(id).forEach(cartItemRepository::delete);
        
        cartDTO
            .getItems()
            .stream()
            .map(item->convertCartItemDTOToCartItem(item,id))
            .map(cartItemRepository::save)
            .toList();

        return convertCartToCartVO(cartInDB);
    }


    CartItem convertCartItemDTOToCartItem(CartItemDTO cartItemDTO, Integer cartId){
        CartItem cartItem=new CartItem();
        cartItem.setCartId(cartId);
        cartItem.setProductId(cartItemDTO.getProduct().getId());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        return cartItem;
    } 

    CartVO convertCartToCartVO(Cart cart){
        List<CartItemVO> items=cartItemRepository.findByCartId(cart.getId())
            .stream()
            .map(this::convertCartItemToCartItemVO)
            .toList();
        return CartVO
            .builder()
            .items(items)
            .totalPrice(getTotalPrice(items))
            .build();
    }

    CartItemVO convertCartItemToCartItemVO(CartItem cartItem){
        ProductVO products= productService.getProductById(cartItem.getProductId());
        return CartItemVO
            .builder()
            .product(products)
            .quantity(cartItem.getQuantity())
            .build();
    }

    Double getTotalPrice(List<CartItemVO> items){
        return items
            .stream()
            .map(item->(item.getProduct().getPrice()*item.getQuantity()))
            .mapToDouble(Double::doubleValue)
            .sum();
    }


}
