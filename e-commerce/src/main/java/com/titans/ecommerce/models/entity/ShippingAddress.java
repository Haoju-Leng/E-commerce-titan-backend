package com.titans.ecommerce.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShippingAddress extends BaseEntity implements Serializable {
    private int userId;
    private String address;
    private String city;
    private String state;
    private String zipcode;


}
