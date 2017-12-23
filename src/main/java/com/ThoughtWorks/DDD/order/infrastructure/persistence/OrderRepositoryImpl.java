package com.ThoughtWorks.DDD.order.infrastructure.persistence;

import com.ThoughtWorks.DDD.order.domain.order.Order;
import com.ThoughtWorks.DDD.order.domain.order.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryImpl extends OrderRepository, JpaRepository<Order, Long> {
}
