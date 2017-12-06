package com.ThoughtWorks.DDD.Order.infrastructure.service;

import com.ThoughtWorks.DDD.Order.domain.payment.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyPaymentService implements PaymentService {
    @Override
    public Boolean pay(Long orderId) {
        return true;
    }
}
