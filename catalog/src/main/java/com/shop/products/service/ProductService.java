package com.shop.products.service;

import com.shop.products.dto.Request.RequestIsTokenValid;
import com.shop.products.dto.Request.RequestProduct;
import com.shop.products.dto.Response.ChargingResponse;
import com.shop.products.dto.Response.IsTokenValid;
import com.shop.products.dto.Response.TokenValid;
import com.shop.products.model.ProductsModel;
import com.shop.products.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    private static RestTemplate restTemplate = null;

    @Autowired
    ProductsRepository productsRepository;


    public ProductService(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }
    @Override
    public List<ProductsModel> findAll(){

        RequestIsTokenValid requestIsTokenValid = new RequestIsTokenValid();
        requestIsTokenValid.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmYWRpYWhAZ21haWwuY29tICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIiwiaWF0IjoxNjg1NjI1MTcxLCJleHAiOjE2ODU2MjY2MTF9.hjZyJOUoaUzRy9VC_E6j7sDWNEqL7J2oq7gxQrMEu7c");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestIsTokenValid> httpEntity = new HttpEntity<>(requestIsTokenValid, httpHeaders);


        IsTokenValid isTokenValid = restTemplate.postForObject(
                "http://localhost:8081/api/auth/validate",
                httpEntity,
                IsTokenValid.class
        );

        if(isTokenValid.getIsValid() == true){
            return productsRepository.findAll();
        }else{
            return null;
        }

    }
    @Override
    public Object orderProduct(RequestProduct requestProduct){
        Optional<ProductsModel> data = productsRepository.findById(requestProduct.getProductId());
        if(data.get().getStock() > 0){
            ProductsModel productsModel = new ProductsModel();
            productsModel.setId(data.get().getId());
            productsModel.setName(data.get().getName());
            productsModel.setPrice(data.get().getPrice());
            productsModel.setStock(data.get().getStock() - requestProduct.getAmount());
            productsRepository.save(productsModel);

            ChargingResponse chargingResponse = new ChargingResponse();
            chargingResponse.setPrice(data.get().getPrice());
            chargingResponse.setAmount(requestProduct.getAmount());
            chargingResponse.setTotal(requestProduct.getAmount() * data.get().getPrice());
            return chargingResponse;
        }else{
           return null;
        }

    }
}
