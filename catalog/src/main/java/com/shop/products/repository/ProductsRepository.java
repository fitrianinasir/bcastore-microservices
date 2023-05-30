package com.shop.products.repository;

import com.shop.products.model.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductsModel, Integer> {

}
