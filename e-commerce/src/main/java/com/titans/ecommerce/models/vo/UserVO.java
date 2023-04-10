package com.titans.ecommerce.models.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String email; // should be @vanderbilt email

    private String firstName;
    private String lastName;
    private String token;

    private String phone;
    private String address;

    private String city;

    private String state;

    private String country;

    private String zipcode;
}
