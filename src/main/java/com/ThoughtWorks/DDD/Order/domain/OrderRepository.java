package com.ThoughtWorks.DDD.Order.domain;

public interface OrderRepository {
    Order findOne(Long id);

    Order save(Order order);
}
