package com.shop.order.service;

import com.shop.order.model.OrderModel;
import com.shop.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService{

    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderModel save(OrderModel orderModel){
        return orderRepository.save(orderModel);
    }
}
