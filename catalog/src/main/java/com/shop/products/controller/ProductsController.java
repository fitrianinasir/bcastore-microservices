package com.shop.products.controller;

import com.shop.products.dto.Data.TokenData;
import com.shop.products.dto.Response.MessageResponse;
import com.shop.products.exceptions.CustomExceptionHandler;
import com.shop.products.model.ProductsModel;
import com.shop.products.repository.ProductsRepository;
import com.shop.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ProductsController {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    ProductService productService;


    @GetMapping("products")
    public @ResponseBody ResponseEntity<MessageResponse> getAllProducts(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){

        try{
            TokenData tokenData = new TokenData();
            tokenData.setToken(token.substring(7));

            List<ProductsModel> data = productService.findAll(tokenData.getToken());
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setStatus(200);
            messageResponse.setMessage("Successfully retrieved products data");
            messageResponse.setData(data);
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        }catch(Exception e){
            return CustomExceptionHandler.exceptionHandler(403, "Forbidden");
        }
    }

    @GetMapping("/product/{id}")
    public @ResponseBody ResponseEntity<MessageResponse> getProductById(@PathVariable("id") Integer id,
                                                                        @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        try{
            TokenData tokenData = new TokenData();
            tokenData.setToken(token.substring(7));
            Optional<ProductsModel> data = productService.findById(id, tokenData.getToken());
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setStatus(200);
            messageResponse.setMessage("Data retrieved successfully");
            messageResponse.setData(data);
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        }catch(Exception e){
            return CustomExceptionHandler.exceptionHandler(403, "Forbidden");
        }

    }

    @PostMapping("/product/create")
    public @ResponseBody ResponseEntity<MessageResponse> createProduct(@RequestBody ProductsModel productsModel,
                                                                       @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        try{
            TokenData tokenData = new TokenData();
            tokenData.setToken(token.substring(7));
            ProductsModel data = productService.save(productsModel, tokenData.getToken());

            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setStatus(200);
            messageResponse.setMessage("Product created successfully");
            messageResponse.setData(data);
            return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
        }catch(Exception e){
            return CustomExceptionHandler.exceptionHandler(403, "Forbidden");
        }

    }

    @PutMapping("/product/update/{id}")
    public @ResponseBody ResponseEntity<MessageResponse> updateProduct(
            @PathVariable("id") Integer id,
            @RequestBody ProductsModel productsModel,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){
        try{
            TokenData tokenData = new TokenData();
            tokenData.setToken(token.substring(7));
            productsModel.setId(id);
            ProductsModel data = productService.save(productsModel, tokenData.getToken());

            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setStatus(200);
            messageResponse.setMessage("Product updated successfully");
            messageResponse.setData(data);
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        }catch(Exception e){
            return CustomExceptionHandler.exceptionHandler(403, "Forbidden");
        }

    }

    @DeleteMapping("/product/delete/{id}")
    public @ResponseBody ResponseEntity<MessageResponse> deleteProduct(@PathVariable("id") Integer id,
                                                                       @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        try{
            TokenData tokenData = new TokenData();
            tokenData.setToken(token.substring(7));
            productService.delete(id, tokenData.getToken());

            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setStatus(200);
            messageResponse.setMessage("Product id " + id + " deleted successfully");
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        }catch(Exception e){
            return CustomExceptionHandler.exceptionHandler(403, "Forbidden");
        }

    }

}

