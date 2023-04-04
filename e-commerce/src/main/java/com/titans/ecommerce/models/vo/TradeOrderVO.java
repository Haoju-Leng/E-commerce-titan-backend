package com.titans.ecommerce.models.vo;

import com.titans.ecommerce.models.entity.TradeOrder;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeOrderVO {

    private Integer orderId;
    private Integer productId;
    private Integer sellerId;
    private Integer buyerId;
    private String deliveryMethod;
    public enum State {Processing, Denied, Completed}
    @Enumerated(EnumType.STRING)
    private TradeOrder.State state;
    private int quantity;
    private double unitPrice;

}
