package com.shop.payment.repository;

import com.shop.payment.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentModel, Integer> {
}
