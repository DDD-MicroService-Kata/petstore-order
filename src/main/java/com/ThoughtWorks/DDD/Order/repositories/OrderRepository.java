package com.ThoughtWorks.DDD.Order.repositories;

import com.ThoughtWorks.DDD.Order.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
        extends CrudRepository<Order, Long> {
}
