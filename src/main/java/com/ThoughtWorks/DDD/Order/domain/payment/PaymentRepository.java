package com.ThoughtWorks.DDD.Order.domain.payment;

public interface PaymentRepository {
    Payment save(Payment payment);

    Payment paymentOf(Long orderId);
}
