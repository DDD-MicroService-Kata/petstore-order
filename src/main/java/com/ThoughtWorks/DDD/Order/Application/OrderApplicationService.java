package com.ThoughtWorks.DDD.Order.Application;

import com.ThoughtWorks.DDD.Order.Application.DTO.OrderDTO;
import com.ThoughtWorks.DDD.Order.domain.Order.Address;
import com.ThoughtWorks.DDD.Order.domain.Order.Customer;
import com.ThoughtWorks.DDD.Order.domain.Order.Order;
import com.ThoughtWorks.DDD.Order.domain.Order.OrderRepository;
import com.ThoughtWorks.DDD.Order.domain.Order.Pet;
import com.ThoughtWorks.DDD.Order.domain.Order.Shop;
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
        Address address = new Address(orderCommand.getProvince(),
                orderCommand.getCity(),
                orderCommand.getArea(),
                orderCommand.getStreet(),
                orderCommand.getMoreDetails());
        Customer customer = new Customer(orderCommand.getName(), address);
        Shop shop = new Shop(orderCommand.getBrand());
        Pet pet = new Pet(orderCommand.getPrice(), orderCommand.getAmount(), orderCommand.getDescription());
        Order order = new Order(customer, shop, pet);
        this.repository.save(order);
        return order;
    }
}
