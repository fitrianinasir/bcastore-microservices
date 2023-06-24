package com.shop.payment.service;

import com.shop.payment.dto.NotificationRequest;
import com.shop.payment.dto.NotificationResponse;
import com.shop.payment.dto.PaymentRequest;
import com.shop.payment.dto.ResponseMessage;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    public List<PushPaymentModel> getPaymentHist(){
        return pushPaymentRepository.findAll();
    }

    public PushPaymentModel pushPayment(PaymentRequest paymentRequest){
        PushPaymentModel pushPaymentModel = new PushPaymentModel();
        pushPaymentModel.setOrder_id(paymentRequest.getOrder_id());
        pushPaymentModel.setPayment_type(paymentRequest.getPayment_type());
        pushPaymentModel.setTotal_payment(paymentRequest.getTotal_payment());
        pushPaymentModel.setPayment_date(paymentRequest.getPayment_date());
        pushPaymentModel.setStatus_payment("SUCCESS");
        return pushPaymentRepository.save(pushPaymentModel);
    }


    public NotificationResponse pushNotification(PaymentRequest paymentRequest){
        System.out.println("Push Notif Payment Called");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        String bodyNotification =
                paymentRequest.getProduct_name() + "\t \t" + paymentRequest.getAmount() + "\t \t \t" + paymentRequest.getPrice() + "\t \t" + paymentRequest.getAmount();


        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setOrder_id(paymentRequest.getOrder_id());
        notificationRequest.setRecipient(paymentRequest.getRecipient());
        notificationRequest.setBody(bodyNotification);
        notificationRequest.setSend_date(dateFormat.format(date));

        NotificationResponse result = webClient.post()
                .uri(notifUrl)
                .body(Mono.just(notificationRequest), NotificationRequest.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(NotificationResponse.class).block();
        return result;
    }
}

