package com.titans.ecommerce.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TradeOrderDetail extends BaseEntity{

    @ManyToOne
    private TradeOrder tradeOrder;

    @ManyToOne
    private User seller;

    @OneToOne
    private Product product;

    private int quantity;

    private double unitPrice;

}
