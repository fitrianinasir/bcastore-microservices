package com.shop.products.controller;

import com.shop.products.dto.Request.RequestProduct;
import com.shop.products.dto.Response.MessageResponse;
import com.shop.products.repository.ProductsRepository;
import com.shop.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product-trx")
@RequiredArgsConstructor
public class ProductTransactionController {

    @Autowired
    private ProductsRepository productsRepository;
    ProductService productService;

    @PostMapping("/order")
    public @ResponseBody ResponseEntity<MessageResponse> orderProduct(@RequestBody RequestProduct requestProduct){
        System.out.println(requestProduct);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(200);
        messageResponse.setMessage("Ordered Successfully");
        messageResponse.setData(productService.orderProduct(requestProduct));
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
