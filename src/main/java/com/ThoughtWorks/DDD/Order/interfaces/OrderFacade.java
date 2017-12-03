package com.ThoughtWorks.DDD.Order.interfaces;

import com.ThoughtWorks.DDD.Order.domain.*;
import com.ThoughtWorks.DDD.Order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.lang.String.format;
import static java.net.URI.create;

@RestController
@RequestMapping("/api/orders")
public class OrderFacade {

    private final OrderRepository repository;


    @Autowired
    public OrderFacade(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public final ApiForResponse<Order> findById(@PathVariable("id") final long id) {
        Order order = repository.findOne(id);
        return new ApiForResponse<>(order.getId(), order);
    }

    @PostMapping
    public final ResponseEntity createOrder(@RequestBody final ApiForRequest<OrderDTO> req) {
        OrderDTO attributes = req.getAttributes();
        Address address = new Address(attributes.getProvince(),
                attributes.getCity(),
                attributes.getArea(),
                attributes.getStreet(),
                attributes.getMoreDetails());
        Customer customer = new Customer(attributes.getName(), address);
        Shop shop = new Shop(attributes.getBrand());
        Pet pet = new Pet(attributes.getPrice(), attributes.getAmount(), attributes.getDescription());
        Order order = new Order(customer, shop, pet);
        repository.save(order);

        return buildResponseEntity(create(format("/api/orders/%d", order.getId())), HttpStatus.CREATED);
    }


    private ResponseEntity buildResponseEntity(URI location, HttpStatus noContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, noContent);
    }
}

