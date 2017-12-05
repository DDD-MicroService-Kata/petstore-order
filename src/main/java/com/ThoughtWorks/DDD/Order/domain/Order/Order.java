package com.ThoughtWorks.DDD.Order.domain.Order;

import com.ThoughtWorks.DDD.Order.domain.common.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@javax.persistence.Entity(name = "pet_order")
public class Order implements Entity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private Customer customer;
    private Shop shop;
    private Pet pet;
    @CreatedDate
    private ZonedDateTime createdDate = ZonedDateTime.now();

    public Order() {
    }

    public Order(Customer customer, Shop shop, Pet pet) {
        this.customer = customer;
        this.shop = shop;
        this.pet = pet;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean sameIdentityAs(Long otherId) {
        return this.id.equals(otherId);
    }
}
