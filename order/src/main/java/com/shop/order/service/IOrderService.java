package com.shop.order.service;

import com.shop.order.dto.MessageResponse;
import com.shop.order.model.OrderModel;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface IOrderService{

    OrderModel save(OrderModel orderModel);
    OrderModel saveWithPayment(OrderModel orderModel);
    Optional<OrderModel> findById(Integer id);


    List<OrderModel> findAll();

}
