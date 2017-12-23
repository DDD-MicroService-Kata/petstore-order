package com.ThoughtWorks.DDD.order.infrastructure.service;

import com.ThoughtWorks.DDD.order.domain.pet.PetPurchaseService;
import com.ThoughtWorks.DDD.order.infrastructure.client.PetClient;
import com.ThoughtWorks.DDD.order.infrastructure.client.PetStatus;
import com.ThoughtWorks.DDD.order.infrastructure.client.PetStatusChanged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslatePetPurchaseService implements PetPurchaseService {
    private final PetClient petClient;

    @Autowired
    public TranslatePetPurchaseService(PetClient petClient) {
        this.petClient = petClient;
    }

    @Override
    public void lockPetOfOrder(String petId) {
        petClient.changeStatus(new PetStatusChanged(petId, PetStatus.Locked));
    }

    @Override
    public void Return(String petId) {
        petClient.changeStatus(new PetStatusChanged(petId, PetStatus.ForSale));
    }
}