package com.ThoughtWorks.DDD.Order.domain.order;

public interface OrderRepository {
    Order findOne(Long id);

    Order save(Order order);
}
