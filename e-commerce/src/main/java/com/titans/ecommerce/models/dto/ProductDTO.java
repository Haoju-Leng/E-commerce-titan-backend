package com.titans.ecommerce.models.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String productCategory;

    private String description;

    private String manufacturer;

    private String name;

    private double price;

    private Integer stock;

    private int sellerId;
}
