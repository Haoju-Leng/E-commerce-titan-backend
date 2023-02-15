package com.titans.ecommerce.models.dto;

import com.titans.ecommerce.models.entity.Product;
import com.titans.ecommerce.models.entity.ProductFile;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class ProductVO {

    public Integer id;
    private String productCategory;

    private String description;

    private String manufacturer;

    private String name;

    private double price;

    private Integer stock;

    private int sellerId;

    private List<Integer> productFileIdList;

    private Date createTime;

    public enum State {forSale, inTransaction, soldOut}
    @Enumerated(EnumType.STRING)
    private Product.State state;

}
