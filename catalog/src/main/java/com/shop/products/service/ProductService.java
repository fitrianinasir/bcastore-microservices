package com.shop.products.service;

import com.shop.products.dto.Request.RequestIsTokenValid;
import com.shop.products.dto.Request.RequestProduct;
import com.shop.products.dto.Response.ChargingResponse;
import com.shop.products.dto.Response.IsTokenValid;
import com.shop.products.model.ProductsModel;
import com.shop.products.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
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

    @Value("${auth.url}")
    private String authUrl;


    public ProductService(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    public Boolean checkTokenIsValid(String token){
        RequestIsTokenValid requestIsTokenValid = new RequestIsTokenValid();
        requestIsTokenValid.setToken(token);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestIsTokenValid> httpEntity = new HttpEntity<>(requestIsTokenValid, httpHeaders);

        IsTokenValid isTokenValid = restTemplate.postForObject(
                authUrl,
                httpEntity,
                IsTokenValid.class
        );
        return isTokenValid.getIsValid();
    }


    @Override
    public List<ProductsModel> findAll(String token){
        Boolean isTokenValid = checkTokenIsValid(token);
        if(isTokenValid == true){
            return productsRepository.findAll();
        }else{
            return null;
        }
    }

    @Override
    public Optional<ProductsModel> findById(Integer id, String token){
        Boolean isTokenValid = checkTokenIsValid(token);
        if(isTokenValid == true){
            return productsRepository.findById(id);
        }else{
            return null;
        }
    }

    @Override
    public ProductsModel save(ProductsModel productsModel, String token){
        //        ADD CHECK IF PRODUCT EXIST BEFORE UPDATE
        Boolean isTokenValid = checkTokenIsValid(token);
        if(isTokenValid == true){
            return productsRepository.save(productsModel);
        }else{
            return null;
        }
    }

    @Override
    public Object delete(Integer id, String token){
//        ADD CHECK IF PRODUCT EXIST
        Boolean isTokenValid = checkTokenIsValid(token);
        if(isTokenValid == true){
            productsRepository.deleteById(id);
            return 1;
        }else{
            return null;
        }
    }
    @Override
    public Object orderProduct(RequestProduct requestProduct, String token){
        Boolean isTokenValid = checkTokenIsValid(token);
        if(isTokenValid == true){
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
        }else{
            return null;
        }


    }
}
