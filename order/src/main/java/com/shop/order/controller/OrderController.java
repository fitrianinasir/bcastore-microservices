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

    @PostMapping("/place-order2")
    public @ResponseBody ResponseEntity<MessageResponse> placeOrder2(@RequestBody OrderModel orderModel){
        MessageResponse messageResponse = new MessageResponse();
        OrderModel data = orderService.saveWithPayment(orderModel);
        System.out.println("my data is " + data);
        if(data == null){
            messageResponse.setStatus(409);
            messageResponse.setMessage("Failed to order, your balance is not enough");
            messageResponse.setData(null);
            return new ResponseEntity<>(messageResponse, HttpStatus.CONFLICT);

        }else{
            messageResponse.setStatus(200);
            messageResponse.setMessage("Your order has placed successfully");
            messageResponse.setData(data);
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        }

    }

    @GetMapping("/order-hists")
    public @ResponseBody ResponseEntity<MessageResponse> getAllOrderHist(){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(200);
        messageResponse.setMessage("All Data Retrieved Successfully");
        messageResponse.setData(orderService.findAll());
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
    @GetMapping("/order-hist/{id}")
    public @ResponseBody ResponseEntity<MessageResponse> getOrderHistById(@PathVariable("id") Integer id){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(200);
        messageResponse.setMessage("Data is found");
        messageResponse.setData(orderService.findById(id));
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
