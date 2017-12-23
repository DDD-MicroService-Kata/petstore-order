package com.thoughtworks.ddd.order.infrastructure.persistence;

import com.thoughtworks.ddd.order.domain.payment.Payment;
import com.thoughtworks.ddd.order.domain.payment.PaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepositoryImpl extends PaymentRepository, JpaRepository<Payment, Long> {
    @Override
    @Query("select payment from Payment payment where payment.orderId = ?1")
    Payment paymentOf(Long orderId);
}
