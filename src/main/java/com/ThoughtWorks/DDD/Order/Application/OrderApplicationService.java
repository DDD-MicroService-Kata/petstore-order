package com.ThoughtWorks.DDD.Order.Application;

import com.ThoughtWorks.DDD.Order.Application.DTO.OrderDTO;
import com.ThoughtWorks.DDD.Order.domain.order.Order;
import com.ThoughtWorks.DDD.Order.domain.order.OrderRepository;
import com.ThoughtWorks.DDD.Order.domain.order.Pet;
import com.ThoughtWorks.DDD.Order.domain.payment.PaymentService;
import com.ThoughtWorks.DDD.Order.domain.pet.PetPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderApplicationService {
    private final OrderRepository repository;
    private final PetPurchaseService petPurchaseService;
    private final PaymentService paymentService;

    @Autowired
    public OrderApplicationService(OrderRepository repository,
                                   PetPurchaseService petPurchaseService,
                                   PaymentService paymentService) {
        this.repository = repository;
        this.petPurchaseService = petPurchaseService;
        this.paymentService = paymentService;
    }

    public Order bookPet(OrderDTO orderCommand) {
        Pet pet = orderCommand.getPet();
        petPurchaseService.lockPetOfOrder(pet.getPetId());

        Order order = new Order(orderCommand.getCustomer(),
                orderCommand.getShop(),
                pet);
        this.repository.save(order);
        return order;
    }


    public void payOrder(Long orderId) {
        Order order = repository.findOne(orderId);
        if(paymentService.pay(order.getId())) {
            order.paid();
            repository.save(order);
        }
    }
}
