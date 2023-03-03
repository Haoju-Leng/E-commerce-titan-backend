package com.titans.ecommerce.models.vo;

import com.titans.ecommerce.models.dto.ProductVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemVO {

    private ProductVO product;

    private int quantity;
}
