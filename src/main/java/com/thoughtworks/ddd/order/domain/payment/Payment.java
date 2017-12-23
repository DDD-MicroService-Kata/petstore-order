package com.thoughtworks.ddd.order.domain.payment;

import com.thoughtworks.ddd.order.domain.common.Entity;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@javax.persistence.Entity
public class Payment implements Entity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long orderId;
    @Enumerated(STRING)
    private PaymentStatus paymentStatus;

    public Payment(Long orderId, PaymentStatus paymentStatus) {
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
    }

    public Payment() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean sameIdentityAs(Long otherId) {
        return id.equals(otherId);
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void paid() {
        paymentStatus = PaymentStatus.PAID;
    }

    public void waitToRefund() {
        paymentStatus = PaymentStatus.WaitToRefund;
    }
}
