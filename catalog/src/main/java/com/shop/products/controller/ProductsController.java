package com.shop.products.controller;

import com.shop.products.dto.Response.MessageResponse;
import com.shop.products.exceptions.CustomExceptionHandler;
import com.shop.products.model.ProductsModel;
import com.shop.products.repository.ProductsRepository;
import com.shop.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ProductsController {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    ProductService productService;


    @GetMapping("products")
    public @ResponseBody ResponseEntity<MessageResponse> getAllProducts(){

        try{
            List<ProductsModel> data = productService.findAll();
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
    public @ResponseBody ResponseEntity<MessageResponse> getProductById(@PathVariable("id") Integer id){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(200);
        messageResponse.setMessage("Data retrieved successfully");
        messageResponse.setData(productsRepository.findById(id));
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/product/create")
    public @ResponseBody ResponseEntity<MessageResponse> createProduct(@RequestBody ProductsModel productsModel){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(200);
        messageResponse.setMessage("Product created successfully");
        messageResponse.setData(productsRepository.save(productsModel));
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PutMapping("/product/update/{id}")
    public @ResponseBody ResponseEntity<MessageResponse> updateProduct(
            @PathVariable("id") Integer id,
            @RequestBody ProductsModel productsModel
    ){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(200);
        messageResponse.setMessage("Product updated successfully");
        productsModel.setId(id);
        messageResponse.setData(productsRepository.save(productsModel));
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @DeleteMapping("/product/delete/{id}")
    public @ResponseBody ResponseEntity<MessageResponse> deleteProduct(@PathVariable("id") Integer id){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(200);
        messageResponse.setMessage("Product id " + id + " deleted successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

}

