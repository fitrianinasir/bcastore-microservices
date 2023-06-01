package com.shop.products.service;

import com.shop.products.dto.Request.RequestProduct;
import com.shop.products.dto.Response.ChargingResponse;
import com.shop.products.model.ProductsModel;
import com.shop.products.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    ProductsRepository productsRepository;

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
