package com.titans.ecommerce.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart extends BaseEntity {
    @OneToOne
    private User user;

    // @OneToMany
    // List<CartItem> cartItemList;

}
