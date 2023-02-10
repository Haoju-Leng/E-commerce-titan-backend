package com.titans.ecommerce.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseEntity{

    private String productCategory;

    private String productDescription;

    private String productManufacturer;

    private String productName;

    private double productPrice;

    private String unitStock;

    private int sellerId;

    public enum State {forSale, inTransaction, soldOut}
    @Enumerated(EnumType.STRING)
    private State state;


}

