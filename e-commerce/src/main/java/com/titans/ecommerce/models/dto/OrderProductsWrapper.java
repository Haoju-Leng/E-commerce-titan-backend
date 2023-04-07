package com.titans.ecommerce.models.dto;

import java.util.List;

public class OrderProductsWrapper {
    private List<OrderProductInfo> productsInfo;

    public List<OrderProductInfo> getProductsInfo() {
        return productsInfo;
    }

    public void setProductsInfo(List<OrderProductInfo> productsInfo) {
        this.productsInfo = productsInfo;
    }
}
