package com.ThoughtWorks.DDD.Order.domain;

import com.ThoughtWorks.DDD.Order.common.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Shop implements ValueObject<Shop> {
    private String brand;

    public Shop() {
    }

    public Shop(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public boolean sameValueAs(Shop other) {
        return other.brand.equals(brand);
    }
}
