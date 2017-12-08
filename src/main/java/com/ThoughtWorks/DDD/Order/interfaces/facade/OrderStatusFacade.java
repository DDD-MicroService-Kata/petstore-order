package com.ThoughtWorks.DDD.Order.interfaces.facade;

import com.ThoughtWorks.DDD.Order.Application.DTO.OrderDTO;
import com.ThoughtWorks.DDD.Order.Application.OrderApplicationService;
import com.ThoughtWorks.DDD.Order.Application.OrderQueryService;
import com.ThoughtWorks.DDD.Order.domain.order.Order;
import com.ThoughtWorks.DDD.Order.interfaces.common.ApiForRequest;
import com.ThoughtWorks.DDD.Order.interfaces.common.ApiForResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static java.lang.String.format;
import static java.net.URI.create;

@RestController
@RequestMapping("/api/orders")
public class OrderStatusFacade extends HttpFacadeBaseClass {

    private final OrderApplicationService orderApplicationService;


    @Autowired
    public OrderStatusFacade(OrderApplicationService orderApplicationService, OrderQueryService orderQueryService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping("/{id}/status/canceled")
    @ResponseStatus(HttpStatus.CREATED)
    public final void createOrder(@PathVariable("id") final long id) {
        throw new NotImplementedException();
    }
}

