package com.ThoughtWorks.DDD.Order.infrastructure.persistence;

import com.ThoughtWorks.DDD.Order.domain.Order.Order;
import com.ThoughtWorks.DDD.Order.domain.Order.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryImpl extends OrderRepository, JpaRepository<Order, Long> {
}
