package com.ThoughtWorks.DDD.order.interfaces.facade;

import com.ThoughtWorks.DDD.order.application.DTO.OrderDTO;
import com.ThoughtWorks.DDD.order.application.OrderApplicationService;
import com.ThoughtWorks.DDD.order.application.OrderQueryService;
import com.ThoughtWorks.DDD.order.domain.order.Order;
import com.ThoughtWorks.DDD.order.interfaces.common.ApiForRequest;
import com.ThoughtWorks.DDD.order.interfaces.common.ApiForResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;
import static java.net.URI.create;

@RestController
@RequestMapping("/api/orders")
public class OrderFacade extends HttpFacadeBaseClass {

    private final OrderApplicationService orderApplicationService;
    private OrderQueryService orderQueryService;


    @Autowired
    public OrderFacade(OrderApplicationService orderApplicationService, OrderQueryService orderQueryService) {
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
        Order order = orderApplicationService.bookPet(attributes);

        return buildResponseEntity(create(format("/api/orders/%d", order.getId())), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public final void createOrder(@PathVariable("id") final long id) {
        orderApplicationService.payOrder(id);
    }
}
