package com.titans.ecommerce.models.vo;

import com.titans.ecommerce.models.entity.Product;

import lombok.Data;

@Data
public class CartItemVO {

    private Product product;

    private int quantity;
}
