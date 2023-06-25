package com.shop.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushPaymentResponse {
    private Integer status;
    private String message;
    private PushPaymentResponseData data;
}
