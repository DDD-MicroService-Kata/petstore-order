package com.thoughtworks.ddd.order.domain.payment;

public interface PaymentRepository {
    Payment save(Payment payment);

    Payment paymentOf(Long orderId);
}
