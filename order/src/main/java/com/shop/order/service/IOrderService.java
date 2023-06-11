package com.shop.order.service;

import com.shop.order.dto.MessageResponse;
import com.shop.order.model.OrderModel;
import reactor.core.publisher.Mono;

public interface IOrderService {

    OrderModel save(OrderModel orderModel);
}
