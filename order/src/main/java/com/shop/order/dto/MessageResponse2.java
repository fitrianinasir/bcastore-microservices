package com.shop.order.dto;

import com.shop.order.model.OrderModel;


public class MessageResponse2 {
    private Integer status;
    private String message;
    private OrderModel data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderModel getData() {
        return data;
    }

    public void setData(OrderModel data) {
        this.data = data;
    }
}
