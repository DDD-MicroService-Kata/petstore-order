package com.ThoughtWorks.DDD.Order.infrastructure.service;

import com.ThoughtWorks.DDD.Order.domain.pet.PetPurchaseService;
import com.ThoughtWorks.DDD.Order.infrastructure.client.PetClient;
import com.ThoughtWorks.DDD.Order.infrastructure.client.PetStatus;
import com.ThoughtWorks.DDD.Order.infrastructure.client.PetStatusChanged;
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
}
