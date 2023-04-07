package com.titans.ecommerce.models.dto;

public class OrderProductInfo {
    private Integer productId;
    private String method;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
