package com.shop.customers.repository;

import com.shop.customers.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<CustomerModel, Integer> {
    Optional<CustomerModel> findByEmail(String email);
}
