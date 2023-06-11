package com.api.gateway.dto;

import com.api.gateway.dto.data.Products;

public class ResponseMessage<T> {

        private Integer status;
        private String message;
        private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
