package com.thoughtworks.ddd.order.domain.order;

import com.thoughtworks.ddd.order.domain.common.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

import static javax.persistence.EnumType.STRING;

@javax.persistence.Entity(name = "pet_order")
public class Order implements Entity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private Customer customer;
    private Shop shop;
    private Pet pet;
    @Enumerated(STRING)
    private OrderStatus orderStatus = OrderStatus.NOT_COMPLETED;

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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public boolean sameIdentityAs(Long otherId) {
        return this.id.equals(otherId);
    }

    public void completed() {
        orderStatus = OrderStatus.COMPLETED;
    }

    public void canceled() {
        orderStatus = OrderStatus.Cancled;
    }
}
