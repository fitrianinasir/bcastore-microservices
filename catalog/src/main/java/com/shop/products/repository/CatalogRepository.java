package com.shop.products.repository;

import com.shop.products.model.CatalogModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<CatalogModel, Integer> {

}
