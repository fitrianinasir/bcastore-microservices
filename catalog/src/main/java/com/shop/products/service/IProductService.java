package com.shop.products.service;

import com.shop.products.dto.Request.RequestProduct;
import com.shop.products.model.ProductsModel;

import java.util.List;

public interface IProductService {

    List<ProductsModel> findAll();
    Object orderProduct(RequestProduct requestProduct);
}
