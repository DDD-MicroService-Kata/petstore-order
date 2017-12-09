package com.thoughtworks.ddd.order.interfaces.controller;

import com.thoughtworks.ddd.order.application.dto.OrderDTO;
import com.thoughtworks.ddd.order.application.OrderApplicationService;
import com.thoughtworks.ddd.order.application.OrderQueryService;
import com.thoughtworks.ddd.order.domain.order.Order;
import com.thoughtworks.ddd.order.interfaces.common.ApiForRequest;
import com.thoughtworks.ddd.order.interfaces.common.ApiForResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;
import static java.net.URI.create;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController {

    private final OrderApplicationService orderApplicationService;
    private OrderQueryService orderQueryService;


    @Autowired
    public OrderController(OrderApplicationService orderApplicationService, OrderQueryService orderQueryService) {
        this.orderApplicationService = orderApplicationService;
        this.orderQueryService = orderQueryService;
    }

    @GetMapping("/{id}")
    public final ApiForResponse<Order> findById(@PathVariable("id") final long id) {
        Order order = orderQueryService.queryOrder(id);
        ApiForResponse<Order> orderApiForResponse = new ApiForResponse<>(order.getId(), order);
        return orderApiForResponse;
    }

    @PostMapping
    public final ResponseEntity createOrder(@RequestBody final ApiForRequest<OrderDTO> req) {
        OrderDTO attributes = req.getAttributes();
        Order order = orderApplicationService.bookPet(new Order(attributes.getCustomer(), attributes.getShop(), attributes.getPet()));

        return buildResponseEntity(create(format("/api/orders/%d", order.getId())), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public final void payOrder(@PathVariable("id") final long id) {
        orderApplicationService.payOrder(id);
    }
}

