package com.titans.ecommerce.models.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    ProductVO product;
    int quantity;
}
