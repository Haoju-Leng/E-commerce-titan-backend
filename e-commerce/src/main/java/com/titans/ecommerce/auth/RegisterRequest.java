package com.titans.ecommerce.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstName;
  private String lastName;
  private String email;
  private String password;

  private String phone;

  private String address;

  private String city;

  private String state;

  private String country;

  private String zipcode;
}
