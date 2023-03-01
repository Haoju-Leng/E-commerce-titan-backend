package com.titans.ecommerce.models.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {
    List<CartItemDTO> items;
    Integer userId;
}
