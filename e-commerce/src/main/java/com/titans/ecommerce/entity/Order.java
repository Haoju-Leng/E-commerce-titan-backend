package com.titans.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private User buyer;

    // an order may include multiple sellers, it's better to record the seller in an orderdetail object
    // private User seller;

    @OneToMany
    private List<OrderDetail> orderDetailList;

    private double totalAmount;

    private Timestamp timeStamp;

    public enum State {incomplete, complete}
    @Enumerated(EnumType.STRING)
    private State state;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", buyer=" + buyer +
                ", orderDetailList=" + orderDetailList +
                ", totalAmount=" + totalAmount +
                ", timeStamp=" + timeStamp +
                ", state=" + state +
                '}';
    }
}
