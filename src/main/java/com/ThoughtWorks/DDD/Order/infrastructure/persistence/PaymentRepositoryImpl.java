package com.ThoughtWorks.DDD.Order.infrastructure.persistence;

import com.ThoughtWorks.DDD.Order.domain.payment.Payment;
import com.ThoughtWorks.DDD.Order.domain.payment.PaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepositoryImpl extends PaymentRepository, JpaRepository<Payment, Long> {
    @Override
    @Query("select payment from Payment payment where payment.orderId = ?1")
    Payment paymentOf(Long id);
}
