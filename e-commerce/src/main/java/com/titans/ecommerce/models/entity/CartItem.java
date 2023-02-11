package com.titans.ecommerce.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem extends BaseEntity {
    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    private int quantity;

}
