package com.titans.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TradeOrder extends BaseEntity {
    @ManyToOne
    private User buyer;

    // an order may include multiple sellers, it's better to record the seller in an orderdetail object
    // private User seller;

    @OneToMany
    private List<TradeOrderDetail> tradeOrderDetailList;

    private double totalAmount;
    public enum State {incomplete, complete}
    @Enumerated(EnumType.STRING)
    private State state;

}
