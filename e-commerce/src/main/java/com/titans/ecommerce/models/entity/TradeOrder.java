package com.titans.ecommerce.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TradeOrder extends BaseEntity{
    private Integer productId;
    private Integer sellerId;

    private Integer buyerId;
    private int quantity;
    private double unitPrice;
    private String deliveryMethod;
    public enum State {Processing, Denied, Completed}
    @Enumerated(EnumType.STRING)
    private State state;

}
