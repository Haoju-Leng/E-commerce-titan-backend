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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productCategory='" + productCategory + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productManufacturer='" + productManufacturer + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", unitStock='" + unitStock + '\'' +
                ", sellerId=" + sellerId +
                ", state=" + state +
                '}';
    }
}

