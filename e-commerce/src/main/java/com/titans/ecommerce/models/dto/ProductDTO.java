package com.titans.ecommerce.models.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String productCategory;

    private String productDescription;

    private String productManufacturer;

    private String productName;

    private double productPrice;

    private String unitStock;

    private int sellerId;
}
