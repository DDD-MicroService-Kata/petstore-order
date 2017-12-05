package com.ThoughtWorks.DDD.Order.infrastructure.persistence;

import com.ThoughtWorks.DDD.Order.domain.order.Order;
import com.ThoughtWorks.DDD.Order.domain.order.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryImpl extends OrderRepository, JpaRepository<Order, Long> {
}
