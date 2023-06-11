package com.shop.products.controller;

import com.shop.products.dto.Data.TokenData;
import com.shop.products.dto.Request.RequestProduct;
import com.shop.products.dto.Response.MessageResponse;
import com.shop.products.exceptions.CustomExceptionHandler;
import com.shop.products.repository.ProductsRepository;
import com.shop.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product-trx")
@RequiredArgsConstructor
public class ProductTransactionController {

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ProductService productService;



    @PostMapping("/order")
    public @ResponseBody ResponseEntity<MessageResponse> orderProduct(@RequestBody RequestProduct requestProduct){

        Object res = productService.orderProduct(requestProduct);

        MessageResponse messageResponse = new MessageResponse();
        if(res != null){
            messageResponse.setStatus(200);
            messageResponse.setMessage("Order done Successfully");
            messageResponse.setData(res);
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        }else{
            messageResponse.setStatus(400);
            messageResponse.setMessage("Failed to order, stock unavailable");
            messageResponse.setData(null);
            return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
        }

    }
}
