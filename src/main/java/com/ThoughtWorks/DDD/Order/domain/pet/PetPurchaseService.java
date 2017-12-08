package com.ThoughtWorks.DDD.Order.domain.pet;

public interface PetPurchaseService {
    public void lockPetOfOrder(String petId);

    public void Return(String petId);
}
