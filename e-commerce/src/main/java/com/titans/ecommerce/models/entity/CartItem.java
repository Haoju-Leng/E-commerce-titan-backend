package com.titans.ecommerce.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem extends BaseEntity {
    private Integer cartId;

    private Integer productId;

    private int quantity;

}
