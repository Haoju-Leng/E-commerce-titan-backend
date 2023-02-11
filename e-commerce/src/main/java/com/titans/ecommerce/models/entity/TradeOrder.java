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
