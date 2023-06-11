package com.shop.products.service;

import com.shop.products.dto.Request.RequestProduct;
import com.shop.products.model.ProductsModel;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<ProductsModel> findAll();
    Optional<ProductsModel> findById(Integer id);

    ProductsModel save(ProductsModel productsModel);


    void delete(Integer id);
    Object orderProduct(RequestProduct requestProduct);
}
