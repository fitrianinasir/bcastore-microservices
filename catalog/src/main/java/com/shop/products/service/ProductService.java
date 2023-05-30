package com.shop.products.service;

import com.shop.products.dto.Request.RequestProduct;
import com.shop.products.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{

    @Autowired
    ProductsRepository productsRepository;
    public Object orderProduct(RequestProduct requestProduct){
        return //
    }
}
