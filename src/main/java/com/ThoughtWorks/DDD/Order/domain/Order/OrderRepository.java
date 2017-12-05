package com.ThoughtWorks.DDD.Order.domain.Order;

public interface OrderRepository {
    Order findOne(Long id);

    Order save(Order order);
}
