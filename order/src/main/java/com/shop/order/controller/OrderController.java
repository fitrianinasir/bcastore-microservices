package com.shop.order.controller;

import com.shop.order.dto.MessageResponse;
import com.shop.order.model.OrderModel;
import com.shop.order.repository.OrderRepository;
import com.shop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place-order")
    public @ResponseBody ResponseEntity<MessageResponse> placeOrder(@RequestBody OrderModel orderModel){
        MessageResponse messageResponse = new MessageResponse();
        OrderModel data = orderService.save(orderModel);
        messageResponse.setStatus(200);
        messageResponse.setMessage("Your order has placed successfully");
        messageResponse.setData(data);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
