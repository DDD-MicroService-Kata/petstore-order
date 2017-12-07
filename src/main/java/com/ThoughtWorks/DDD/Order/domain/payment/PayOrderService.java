package com.ThoughtWorks.DDD.Order.domain.payment;

import com.ThoughtWorks.DDD.Order.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayOrderService {
    private PaymentRepository paymentRepository;

    @Autowired
    public PayOrderService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment payOrder(Order order) {
        if (order == null) {
            throw new RuntimeException("order is invalid.");
        }
        Payment payment = paymentRepository.paymentOf(order.getId());
        payment.paid();
        return payment;
    }
}
