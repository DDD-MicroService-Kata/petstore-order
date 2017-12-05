package com.ThoughtWorks.DDD.Order.Application;

import com.ThoughtWorks.DDD.Order.Application.DTO.OrderDTO;
import com.ThoughtWorks.DDD.Order.domain.order.Customer;
import com.ThoughtWorks.DDD.Order.domain.order.Order;
import com.ThoughtWorks.DDD.Order.domain.order.OrderRepository;
import com.ThoughtWorks.DDD.Order.domain.order.Pet;
import com.ThoughtWorks.DDD.Order.domain.order.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderApplicationService {
    private final OrderRepository repository;

    @Autowired
    public OrderApplicationService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order bookPet(OrderDTO orderCommand) {
        Order order = new Order(orderCommand.getCustomer(),
                orderCommand.getShop(),
                orderCommand.getPet());
        this.repository.save(order);
        return order;
    }

}
