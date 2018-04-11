package com.thoughtworks.ddd.order.interfaces.controller;

import com.thoughtworks.ddd.order.application.OrderApplicationService;
import com.thoughtworks.ddd.order.application.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderStatusFacade extends BaseController {

    private final OrderApplicationService orderApplicationService;


    @Autowired
    public OrderStatusFacade(OrderApplicationService orderApplicationService, OrderQueryService orderQueryService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping("/{id}/status/canceled")
    @ResponseStatus(HttpStatus.CREATED)
    public final void createOrder(@PathVariable("id") final long id) {
        orderApplicationService.payOrder(id);
    }
}

