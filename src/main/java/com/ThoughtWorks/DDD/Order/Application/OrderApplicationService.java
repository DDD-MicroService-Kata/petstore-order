package com.ThoughtWorks.DDD.Order.Application;

import com.ThoughtWorks.DDD.Order.Application.DTO.OrderDTO;
import com.ThoughtWorks.DDD.Order.domain.order.Order;
import com.ThoughtWorks.DDD.Order.domain.order.OrderRepository;
import com.ThoughtWorks.DDD.Order.domain.order.Pet;
import com.ThoughtWorks.DDD.Order.domain.pet.PetPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderApplicationService {
    private final OrderRepository repository;
    private final PetPurchaseService petPurchaseService;

    @Autowired
    public OrderApplicationService(OrderRepository repository,
                                   PetPurchaseService petPurchaseService) {
        this.repository = repository;
        this.petPurchaseService = petPurchaseService;
    }

    public Order bookPet(OrderDTO orderCommand) {
        Pet pet = orderCommand.getPet();
        petPurchaseService.lockPetOfOrder(pet.getId());

        Order order = new Order(orderCommand.getCustomer(),
                orderCommand.getShop(),
                pet);
        this.repository.save(order);
        return order;
    }

}
