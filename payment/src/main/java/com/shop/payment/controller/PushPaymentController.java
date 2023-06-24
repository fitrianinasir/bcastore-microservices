package com.shop.payment.controller;

import com.shop.payment.dto.NotificationResponse;
import com.shop.payment.dto.PaymentRequest;
import com.shop.payment.dto.ResponseData;
import com.shop.payment.dto.ResponseMessage;
import com.shop.payment.model.PaymentModel;
import com.shop.payment.model.PushPaymentModel;
import com.shop.payment.service.PushPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PushPaymentController {
    @Autowired
    PushPaymentService pushPaymentService;
    @GetMapping("/payment-hist")
    public @ResponseBody ResponseEntity<ResponseMessage> getPaymentHist(){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(200);
        responseMessage.setMessage("Successfully retrieved all data of payment hist");
        responseMessage.setData(pushPaymentService.getPaymentHist());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
    @PostMapping("/push-payment")
    public  @ResponseBody ResponseEntity<ResponseMessage>  pushPayment(
            @RequestBody PaymentRequest paymentRequest
            ){
        NotificationResponse notifRes = pushPaymentService.pushNotification(paymentRequest);
        PushPaymentModel paymentRes = pushPaymentService.pushPayment(paymentRequest);


        ResponseData responseData = new ResponseData();
        responseData.setId_payment(paymentRes.getId());
        responseData.setId_notification(notifRes.getData().getId());
        responseData.setRecipient(notifRes.getData().getRecipient());
        responseData.setPayment_status(paymentRes.getStatus_payment());
        responseData.setNotification_status(notifRes.getData().getStatus());

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(200);
        responseMessage.setMessage("Payment pushed successfully");
        responseMessage.setData(responseData);


        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}
