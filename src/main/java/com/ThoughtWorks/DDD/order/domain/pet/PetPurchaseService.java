package com.ThoughtWorks.DDD.order.domain.pet;

public interface PetPurchaseService {
    public void lockPetOfOrder(String petId);

    public void Return(String petId);
}
