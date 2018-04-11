package com.thoughtworks.ddd.order.interfaces.controller;

import com.thoughtworks.ddd.order.application.OrderApplicationService;
import com.thoughtworks.ddd.order.application.OrderQueryService;
import com.thoughtworks.ddd.order.application.dto.OrderDTO;
import com.thoughtworks.ddd.order.domain.order.Order;
import com.thoughtworks.ddd.order.domain.payment.Payment;
import com.thoughtworks.ddd.order.interfaces.common.ApiForRequest;
import com.thoughtworks.ddd.order.interfaces.common.ApiForResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
        OrderDTO orderDTO = req.getAttributes();
        Order order = toOrder(orderDTO);
        Order newOrder = orderApplicationService.bookPet(order);
        return buildResponseEntity(URI.create("/api/orders/" + newOrder.getId()), HttpStatus.CREATED);
    }

    private Order toOrder(OrderDTO orderDTO) {
        return new Order(orderDTO.getCustomer(), orderDTO.getShop(), orderDTO.getPet());
    }

    @GetMapping("/{id}/payments")
    public final ApiForResponse<Payment> getPaymentOfOrder(@PathVariable("id") final long id) {
        Payment payment = orderApplicationService.getPayment(id);
        return new ApiForResponse<>(payment.getId(), payment);
    }

    @PostMapping("/{id}/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public final void payOrder(@PathVariable("id") final long id) {
        orderApplicationService.payOrder(id);
    }
}

