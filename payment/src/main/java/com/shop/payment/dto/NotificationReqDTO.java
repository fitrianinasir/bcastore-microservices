package com.shop.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationReqDTO {
    private Integer id;
    private Integer order_id;
    private Integer customer_id;
    private String recipient;
    private String subject;
    private String body;
    private String send_date;
    private String status;
}
