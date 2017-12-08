package com.ThoughtWorks.DDD.Order.domain.pet;


public interface PetPurchaseService {
    void lockPetOfOrder(String petId);
}
