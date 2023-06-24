package com.shop.payment.service;

import com.shop.payment.model.PushPaymentModel;
import com.shop.payment.repository.PaymentRepository;
import com.shop.payment.repository.PushPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PushPaymentService{
    @Value("${notif.url}")
    private String notifUrl;
    @Autowired
    PushPaymentRepository pushPaymentRepository;

    private final WebClient webClient;
    public PushPaymentService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }

    public PushPaymentModel pushPayment(PushPaymentModel pushPaymentModel){
        return pushPaymentRepository.save(pushPaymentModel);
    }

    public List<PushPaymentModel> getPaymentHist(){
        return pushPaymentRepository.findAll();
    }

}

