package com.shop.customers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String name;
    private String email;
    private String password;
    private Integer pin;
    private Integer accountNumber;
    private Integer balance;
    private String address;
    private String city;
    private String insertDate;
    private String role;
}
