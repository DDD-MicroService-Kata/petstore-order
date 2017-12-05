package com.ThoughtWorks.DDD.Order.infrastructure.client;

public class PetStatusChanged {
    public String petId;
    public PetStatus petStatus;

    public PetStatusChanged() {
    }

    public PetStatusChanged(String petId, PetStatus locked) {
    }
}
