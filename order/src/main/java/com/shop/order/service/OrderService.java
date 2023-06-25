package com.shop.order.service;

import com.shop.order.dto.*;
import com.shop.order.model.OrderModel;
import com.shop.order.repository.OrderRepository;
import jakarta.persistence.criteria.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{

    @Autowired
    OrderRepository orderRepository;

    @Value("${order.url}")
    private String orderUrl;

    @Value("${payment.url}")
    private String paymentUrl;

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

    @Override
    public OrderModel saveWithPayment(OrderModel orderModel){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductId(orderModel.getId_product());
        orderProduct.setAmount(orderModel.getNum_of_orders());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        orderModel.setOrder_date(dateFormat.format(date));

        MessageResponse2 res = webClient.post()
                .uri(orderUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(orderProduct), OrderProduct.class)
                .retrieve()
                .bodyToMono(MessageResponse2.class).block();
        if(res.getStatus()==200){
            OrderModel createModel = orderRepository.save(orderModel);
            OrderModel pushOrder = pushPaymentNotification(createModel);
            return pushOrder;
        }else{
            return null;
        }
    }

    public OrderModel pushPaymentNotification(OrderModel orderModel){
        System.out.println("Calling pushPaymentNotification before");
        PushPaymentDTO pushPaymentDTO = new PushPaymentDTO();
        pushPaymentDTO.setOrder_id(orderModel.getId());
        pushPaymentDTO.setRecipient("fitrianinasir8@gmail.com");
        pushPaymentDTO.setProduct_name(orderModel.getProduct_name());
        pushPaymentDTO.setPayment_type(orderModel.getPayment_name());
        pushPaymentDTO.setAmount(orderModel.getNum_of_orders());
        pushPaymentDTO.setPrice(orderModel.getProduct_price());
        pushPaymentDTO.setTotal_payment(orderModel.getTotal_charging());
        PushPaymentResponse res = webClient.post()
                .uri(paymentUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(pushPaymentDTO), PushPaymentDTO.class)
                .retrieve()
                .bodyToMono(PushPaymentResponse.class).block();

        orderModel.setId(orderModel.getId());
        orderModel.setPayment_status(res.getData().getPayment_status());
        orderModel.setNotification_status(res.getData().getNotification_status());

        return orderRepository.save(orderModel);
    }

    @Override
    public List<OrderModel> findAll(){return orderRepository.findAll();}

    @Override
    public Optional<OrderModel> findById(Integer id){
        return orderRepository.findById(id);
    }
}
