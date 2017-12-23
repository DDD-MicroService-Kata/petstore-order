package com.thoughtworks.ddd.order.infrastructure.persistence;

import com.thoughtworks.ddd.order.domain.order.Order;
import com.thoughtworks.ddd.order.domain.order.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryImpl extends OrderRepository, JpaRepository<Order, Long> {
}
