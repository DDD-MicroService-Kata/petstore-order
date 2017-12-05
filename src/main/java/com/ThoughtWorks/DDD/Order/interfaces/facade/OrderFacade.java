package com.ThoughtWorks.DDD.Order.interfaces.facade;

import com.ThoughtWorks.DDD.Order.Application.OrderApplicationService;
import com.ThoughtWorks.DDD.Order.Application.OrderQueryService;
import com.ThoughtWorks.DDD.Order.domain.Order.Order;
import com.ThoughtWorks.DDD.Order.interfaces.common.ApiForRequest;
import com.ThoughtWorks.DDD.Order.interfaces.common.ApiForResponse;
import com.ThoughtWorks.DDD.Order.Application.DTO.OrderDTO;
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

    private final OrderApplicationService orderApplicationService;
    private OrderQueryService orderQueryService;


    @Autowired
    public OrderFacade(OrderApplicationService orderApplicationService, OrderQueryService orderQueryService) {
        this.orderApplicationService = orderApplicationService;
        this.orderQueryService = orderQueryService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public final ApiForResponse<Order> findById(@PathVariable("id") final long id) {
        Order order = orderQueryService.queryOrder(id);
        return new ApiForResponse<>(order.getId(), order);
    }

    @PostMapping
    public final ResponseEntity createOrder(@RequestBody final ApiForRequest<OrderDTO> req) {
        OrderDTO attributes = req.getAttributes();
        Order order = orderApplicationService.bookPet(attributes);

        return buildResponseEntity(create(format("/api/orders/%d", order.getId())), HttpStatus.CREATED);
    }


    protected ResponseEntity buildResponseEntity(URI location, HttpStatus noContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, noContent);
    }
}

