package com.shop.payment.service;

import com.shop.payment.model.PushPaymentModel;
import com.shop.payment.repository.PaymentRepository;
import com.shop.payment.repository.PushPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushPaymentService {
    @Autowired
    PushPaymentRepository pushPaymentRepository;

    public PushPaymentModel pushPayment(PushPaymentModel pushPaymentModel){
        return pushPaymentRepository.save(pushPaymentModel);

    }

}
