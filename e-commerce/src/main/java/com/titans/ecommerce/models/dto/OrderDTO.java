package com.titans.ecommerce.models.dto;

import lombok.Data;

@Data
public class OrderDTO {
    ProductVO product;
    int quantity;
    String deliveryMethod;
}
