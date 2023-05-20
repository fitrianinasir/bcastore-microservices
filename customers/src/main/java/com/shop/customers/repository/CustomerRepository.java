package com.shop.customers.repository;

import com.shop.customers.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {

    Boolean existsByEmail(String email);
    CustomerModel findByEmail(String email);
    Boolean existsByAccountNumber(Integer accountNumber);
    CustomerModel findByAccountNumber(Integer accountNumber);

}
