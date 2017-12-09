package com.thoughtworks.ddd.order.application;

public interface PetPurchaseService {
    void lockPetOfOrder(String petId);

    void Return(String petId);
}
