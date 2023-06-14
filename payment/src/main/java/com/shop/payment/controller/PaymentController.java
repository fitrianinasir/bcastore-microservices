package com.shop.payment.controller;

import com.shop.payment.dto.ResponseMessage;
import com.shop.payment.model.PaymentModel;
import com.shop.payment.repository.PaymentRepository;
import com.shop.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/payments")
    public @ResponseBody ResponseEntity<ResponseMessage> getAllPaymentType(){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(200);
        responseMessage.setMessage("Successfully retrieved all data");
        responseMessage.setData(paymentService.getAllPaymentType());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/payment/{id}")
    public @ResponseBody ResponseEntity<ResponseMessage> getPaymentTypeById(@PathVariable("id") Integer id){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(200);
        responseMessage.setMessage("Successfully get data");
        responseMessage.setData(paymentService.getPaymentTypeById(id));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/payment")
    public @ResponseBody ResponseEntity<ResponseMessage> createPaymentType(@RequestBody PaymentModel paymentModel){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(200);
        responseMessage.setMessage("Data created successfully");
        responseMessage.setData(paymentService.createPaymentType(paymentModel));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/payment/{id}")
    public @ResponseBody ResponseEntity<ResponseMessage> updatePaymentType(
            @PathVariable("id") Integer id,
            @RequestBody PaymentModel paymentModel){
        paymentModel.setId(id);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(200);
        responseMessage.setMessage("Data updated successfully");
        responseMessage.setData(paymentService.updatePaymentType(paymentModel));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @DeleteMapping("/payment/{id}")
    public @ResponseBody ResponseEntity<ResponseMessage> deletePaymentType(
            @PathVariable("id") Integer id){
        paymentService.deletePaymentType(id);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(200);
        responseMessage.setMessage("Data deleted successfully");
        responseMessage.setData(null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
