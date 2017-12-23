package com.ThoughtWorks.DDD.order.domain.order;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {
    @Test
    public void should_the_order_is_not_completed_just_created() throws Exception {
        assertThat(new Order().getOrderStatus()).isEqualTo(OrderStatus.NOT_COMPLETED);
    }

    @Test
    public void should_change_order_status_to_completed() throws Exception {
        Order order = new Order();
        order.completed();
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.COMPLETED);
    }
}