package com.shop.payment.repository;

import com.shop.payment.model.PushPaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PushPaymentRepository extends JpaRepository<PushPaymentModel, Integer> {
}
