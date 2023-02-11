package com.titans.ecommerce.models.entity;
import jakarta.persistence.*;
import lombok.*;

@Data
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

