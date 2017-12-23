package com.thoughtworks.ddd.order.domain.pet;

public interface PetPurchaseService {
    public void lockPetOfOrder(String petId);

    public void Return(String petId);
}
