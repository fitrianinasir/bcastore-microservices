package com.shop.products.exceptions;

import com.shop.products.dto.Response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomExceptionHandler{
    public static ResponseEntity<MessageResponse>  exceptionHandler(Integer status, String message){
        MessageResponse messageResponse = new MessageResponse();
            messageResponse.setStatus(status);
            messageResponse.setMessage(message);
            messageResponse.setData(null);
            return new ResponseEntity<>(messageResponse, HttpStatus.FORBIDDEN);
    }
}
