package com.titans.ecommerce.models.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseEntity{

    private String productCategory;

    private String description;

    private String manufacturer;

    private String name;

    private double price;

    private Integer stock;

    private int sellerId;
    @OneToMany
    private List<ProductFile> productFileList;

    public enum State {forSale, inTransaction, soldOut}
    @Enumerated(EnumType.STRING)
    private State state;


}

