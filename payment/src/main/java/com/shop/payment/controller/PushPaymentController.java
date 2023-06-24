package com.shop.payment.controller;

import com.shop.payment.dto.ResponseMessage;
import com.shop.payment.model.PushPaymentModel;
import com.shop.payment.service.PushPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PushPaymentController {
    @Autowired
    PushPaymentService pushPaymentService;

    @PostMapping("/push-payment")
    public @ResponseBody ResponseEntity<ResponseMessage> pushPayment(
            @RequestBody PushPaymentModel pushPaymentModel
            ){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(200);
        responseMessage.setMessage("Payment pushed successfully");
        responseMessage.setData(pushPaymentService.pushPayment(pushPaymentModel));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/payment-hist")
    public @ResponseBody ResponseEntity<ResponseMessage> getPaymentHist(){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(200);
        responseMessage.setMessage("Successfully retrieved all data of payment hist");
        responseMessage.setData(pushPaymentService.getPaymentHist());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
