package com.shop.customers.repository;

import com.shop.customers.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {

    Boolean existsByEmail(String email);
//    CustomerModel findbyemail(String email);
    Boolean existsByAccountNumber(Integer accountNumber);
    CustomerModel findByAccountNumber(Integer accountNumber);

    Optional<CustomerModel> findByEmail(String email);

}
