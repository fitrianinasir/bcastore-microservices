package com.shop.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Integer order_id;
    private Integer customer_id;
    private String recipient;
    private String payment_type;
    private String product_name;
    private Integer amount;
    private Integer price;
    private Integer total_payment;
    private String payment_date;
}
