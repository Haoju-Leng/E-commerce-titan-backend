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
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private User seller;

    @OneToOne
    private Product product;

    private int quantity;

    private double unitPrice;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order=" + order +
                ", seller=" + seller +
                ", product=" + product +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
