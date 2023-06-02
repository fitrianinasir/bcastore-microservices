package com.shop.products.service;

import com.shop.products.dto.Request.RequestProduct;
import com.shop.products.model.ProductsModel;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<ProductsModel> findAll(String token);
    Optional<ProductsModel> findById(Integer id, String token);

    ProductsModel save(ProductsModel productsModel, String token);


    Object delete(Integer id, String token);
    Object orderProduct(RequestProduct requestProduct);
}
