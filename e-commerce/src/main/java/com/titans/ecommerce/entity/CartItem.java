package com.titans.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    private int quantity;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", cart=" + cart +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
