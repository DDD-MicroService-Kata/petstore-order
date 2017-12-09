package com.thoughtworks.ddd.order.application;

import com.thoughtworks.ddd.order.domain.OrderService;
import com.thoughtworks.ddd.order.domain.order.Order;
import com.thoughtworks.ddd.order.domain.order.OrderRepository;
import com.thoughtworks.ddd.order.domain.payment.PayOrderService;
import com.thoughtworks.ddd.order.domain.payment.Payment;
import com.thoughtworks.ddd.order.domain.payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderApplicationService {
    private final OrderRepository orderRepository;
    private final PetPurchaseService petPurchaseService;
    private final PayOrderService userPayOrderService;
    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    @Autowired
    public OrderApplicationService(OrderRepository orderRepository,
                                   PetPurchaseService petPurchaseService,
                                   PayOrderService userPayOrderService,
                                   PaymentRepository paymentRepository,
                                   OrderService orderService) {
        this.orderRepository = orderRepository;
        this.petPurchaseService = petPurchaseService;
        this.userPayOrderService = userPayOrderService;
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    public Order bookPet(Order order) {
        Order newOrder = orderService.createOrder(order);
        String petId = newOrder.getPet().getPetId();
        petPurchaseService.lockPetOfOrder(petId);
        return newOrder;
    }


    public void payOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        Payment payment = userPayOrderService.payOrder(order);
        paymentRepository.save(payment);
        order.completed();
        orderRepository.save(order);
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.canceled();
        Payment payment = paymentRepository.paymentOf(orderId);
        payment.waitToRefund();
        petPurchaseService.Return(order.getPet().getPetId());
    }
}
