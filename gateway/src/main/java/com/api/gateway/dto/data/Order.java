package com.api.gateway.dto.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id_customer;
    private String customer_name;
    private Integer id_product;
    private String product_name;
    private Integer product_price;
    private Integer id_payment;
    private String payment_name;
    private Integer num_of_orders;
    private Integer total_charging;
    private String order_date;
}
