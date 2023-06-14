package com.shop.payment.service;

import com.shop.payment.model.PaymentModel;
import com.shop.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public List<PaymentModel> getAllPaymentType(){
        return paymentRepository.findAll();
    }

    public Optional<PaymentModel> getPaymentTypeById(Integer id){
        return paymentRepository.findById(id);
    }

    public PaymentModel createPaymentType(PaymentModel paymentModel){
        return paymentRepository.save(paymentModel);
    }

    public PaymentModel updatePaymentType(PaymentModel paymentModel){
        return paymentRepository.save(paymentModel);
    }

    public void deletePaymentType(Integer id){
        paymentRepository.deleteById(id);
    }
}
