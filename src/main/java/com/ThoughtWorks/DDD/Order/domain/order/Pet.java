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

    public Pet(Integer price, Integer amount, String description) {
        this.price = price;
        this.amount = amount;
        this.description = description;
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

    @Override
    public boolean sameValueAs(Pet other) {
        return other.price.equals(price)
                && other.amount.equals(amount)
                && other.description.equalsIgnoreCase(description);
    }

    public String getId() {
        return petId;
    }
}
