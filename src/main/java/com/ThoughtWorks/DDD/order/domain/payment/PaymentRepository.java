package com.ThoughtWorks.DDD.order.domain.payment;

public interface PaymentRepository {
    Payment save(Payment payment);

    Payment paymentOf(Long orderId);
}
