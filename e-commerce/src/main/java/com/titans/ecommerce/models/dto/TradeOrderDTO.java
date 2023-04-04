package com.titans.ecommerce.models.dto;

import lombok.Data;

@Data
public class TradeOrderDTO {
    ProductVO product;
    int quantity;
    String deliveryMethod;
}
