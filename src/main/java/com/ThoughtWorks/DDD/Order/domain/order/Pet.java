package com.ThoughtWorks.DDD.Order.domain.order;

import com.ThoughtWorks.DDD.Order.domain.common.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Pet implements ValueObject<Pet> {
    private String petId;
    private Integer price;
    private Integer amount;
    private String description;

    public Pet() {
    }

    public Pet(Integer price, Integer amount, String description, String petId) {
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.petId = petId;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getPetId() {
        return petId;
    }

    @Override
    public boolean sameValueAs(Pet other) {
        return other.price.equals(price)
                && other.amount.equals(amount)
                && other.description.equalsIgnoreCase(description)
                && other.petId.equals(petId);
    }


}
