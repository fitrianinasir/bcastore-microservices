package com.shop.products.service;

import com.shop.products.dto.Request.RequestProduct;

public interface IProductService {

    Object orderProduct(RequestProduct requestProduct);
}
