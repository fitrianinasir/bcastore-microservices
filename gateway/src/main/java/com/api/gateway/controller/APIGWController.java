package com.api.gateway.controller;

import com.api.gateway.dto.ResponseMessage;
import com.api.gateway.dto.ResponseTokenValid;
import com.api.gateway.dto.data.Order;
import com.api.gateway.dto.data.Products;
import com.api.gateway.dto.data.Token;
import com.api.gateway.service.GWService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("api-gw")
@RequiredArgsConstructor

public class APIGWController {

    @Autowired
    GWService gwService;

    @GetMapping("products")
    public Mono<ResponseMessage> getAllProducts(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){
        Token tokenData = new Token();
        tokenData.setToken(token.substring(7));
        Mono<ResponseTokenValid> res = gwService.checkIsTokenValid(tokenData);
        if(res.block().getIsValid()==true){
            return gwService.getAllProducts();
        }else{
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(403);
            responseMessage.setMessage("Auth failed");
            responseMessage.setData(null);
            Mono<ResponseMessage> exc = Mono.just(responseMessage);
            return exc;
        }
    }

    @GetMapping("product/{id}")
    public Mono<ResponseMessage> getProductById(
            @PathVariable("id") Integer id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){
        Token tokenData = new Token();
        tokenData.setToken(token.substring(7));
        Mono<ResponseTokenValid> res = gwService.checkIsTokenValid(tokenData);
        if(res.block().getIsValid()==true){
            return gwService.getProductById(id);
        }else{
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(403);
            responseMessage.setMessage("Auth failed");
            responseMessage.setData(null);
            Mono<ResponseMessage> exc = Mono.just(responseMessage);
            return exc;
        }
    }

    @PostMapping("product/create")
    public Mono<ResponseMessage> createProduct(
            @RequestBody Products products,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){
        Token tokenData = new Token();
        tokenData.setToken(token.substring(7));
        Mono<ResponseTokenValid> res = gwService.checkIsTokenValid(tokenData);
        if(res.block().getIsValid()==true){
            return gwService.createProduct(products);
        }else{
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(403);
            responseMessage.setMessage("Auth failed");
            responseMessage.setData(null);
            Mono<ResponseMessage> exc = Mono.just(responseMessage);
            return exc;
        }
    }
    @PutMapping("product/update/{id}")
    public Mono<ResponseMessage> updateProductById(
            @RequestBody Products products,
            @PathVariable("id") Integer id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){
        Token tokenData = new Token();
        tokenData.setToken(token.substring(7));
        Mono<ResponseTokenValid> res = gwService.checkIsTokenValid(tokenData);
        if(res.block().getIsValid()==true){
            return gwService.updateProduct(products, id);
        }else{
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(403);
            responseMessage.setMessage("Auth failed");
            responseMessage.setData(null);
            Mono<ResponseMessage> exc = Mono.just(responseMessage);
            return exc;
        }
    }

    @DeleteMapping("product/delete/{id}")
    public Mono<ResponseMessage> deleteProductById(
            @PathVariable("id") Integer id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){
        Token tokenData = new Token();
        tokenData.setToken(token.substring(7));
        Mono<ResponseTokenValid> res = gwService.checkIsTokenValid(tokenData);
        if(res.block().getIsValid()==true){
            return gwService.deleteProductById(id);
        }else{
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(403);
            responseMessage.setMessage("Auth failed");
            responseMessage.setData(null);
            Mono<ResponseMessage> exc = Mono.just(responseMessage);
            return exc;
        }
    }

    @GetMapping("/order-hists")
    public Flux<ResponseMessage> getAllOrderHist(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        Token tokenData = new Token();
        tokenData.setToken(token.substring(7));
        Mono<ResponseTokenValid> res = gwService.checkIsTokenValid(tokenData);
        if(res.block().getIsValid()==true){
            return gwService.getAllOrderHist();
        }else{
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(403);
            responseMessage.setMessage("Auth failed");
            responseMessage.setData(null);
            Flux<ResponseMessage> exc = Flux.just(responseMessage);
            return exc;
        }
    }

    @GetMapping("/order-hist/{id}")
    public Mono<ResponseMessage> getOrderHistById(@PathVariable("id") Integer id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        Token tokenData = new Token();
        tokenData.setToken(token.substring(7));
        Mono<ResponseTokenValid> res = gwService.checkIsTokenValid(tokenData);
        if(res.block().getIsValid()==true){
            return gwService.getOrderHistById(id);
        }else{
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(403);
            responseMessage.setMessage("Auth failed");
            responseMessage.setData(null);
            Mono<ResponseMessage> exc = Mono.just(responseMessage);
            return exc;
        }
    }

    @PostMapping("product-trx/order")
    public Mono<ResponseMessage> placeOrder(
            @RequestBody Order order,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){
        Token tokenData = new Token();
        tokenData.setToken(token.substring(7));

        Mono<ResponseTokenValid> res = gwService.checkIsTokenValid(tokenData);

        if(res.block().getIsValid()==true){
            return gwService.placeOrder(order);
        }else{
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(403);
            responseMessage.setMessage("Auth failed");
            responseMessage.setData(null);
            Mono<ResponseMessage> exc = Mono.just(responseMessage);
            return exc;
        }
    }

    @PostMapping("product-trx/order-payment")
    public Mono<ResponseMessage> placeOrderWithPaymentNotif(
            @RequestBody Order order,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){
        Token tokenData = new Token();
        tokenData.setToken(token.substring(7));

        Mono<ResponseTokenValid> res = gwService.checkIsTokenValid(tokenData);

        if(res.block().getIsValid()==true){
            return gwService.placeOrderWithPaymentNotif(order);
        }else{
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(403);
            responseMessage.setMessage("Auth failed");
            responseMessage.setData(null);
            Mono<ResponseMessage> exc = Mono.just(responseMessage);
            return exc;
        }
    }
}
