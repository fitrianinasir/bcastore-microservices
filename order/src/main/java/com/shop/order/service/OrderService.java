package com.shop.order.service;

import com.shop.order.dto.MessageResponse;
import com.shop.order.dto.OrderProduct;
import com.shop.order.model.OrderModel;
import com.shop.order.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OrderService implements IOrderService{

    @Autowired
    OrderRepository orderRepository;

    @Value("${order.url}")
    private String orderUrl;

    private final WebClient webClient;
    public OrderService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }

    @Override
    public OrderModel save(OrderModel orderModel){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductId(orderModel.getId_product());
        orderProduct.setAmount(orderModel.getNum_of_orders());
        Mono<MessageResponse> res = webClient.post()
                .uri(orderUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(orderProduct), OrderProduct.class)
                .retrieve()
                .bodyToMono(MessageResponse.class);
        if(res.block().getStatus()==200){
            return orderRepository.save(orderModel);
        }else{
            return null;
        }

    }
}
