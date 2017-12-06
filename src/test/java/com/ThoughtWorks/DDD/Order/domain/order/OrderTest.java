package com.ThoughtWorks.DDD.Order.domain.order;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {
    @Test
    public void should_the_order_is_unpaid_just_created() throws Exception {
        assertThat(new Order().getPaymentStatus()).isEqualTo(PaymentStatus.UNPAID);
    }

    @Test
    public void should_change_order_payment_status_to_paid() throws Exception {
        Order order = new Order();
        order.paid();
        assertThat(order.getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
    }
}