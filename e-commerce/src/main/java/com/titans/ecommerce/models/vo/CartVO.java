package com.titans.ecommerce.models.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {
    List<CartItemVO> items;
    Double totalPrice;

    //@Override
    // public void setItems(List<CartItemVO> cartItemVOs){
    //     items=new ArrayList<>();
    //     cartItemVOs
    //         .forEach(cartItemVO->items.add(cartItemVO));
    // }
}
