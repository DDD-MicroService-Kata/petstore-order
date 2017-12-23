package com.ThoughtWorks.DDD.Order.application.DTO;

import com.ThoughtWorks.DDD.Order.domain.order.Address;
import com.ThoughtWorks.DDD.Order.domain.order.Customer;
import com.ThoughtWorks.DDD.Order.domain.order.Pet;
import com.ThoughtWorks.DDD.Order.domain.order.Shop;

public final class OrderDTO {
    private String name;
    private String province;
    private String city;
    private String area;
    private String street;
    private String moreDetails;
    private Integer price;
    private Integer amount;
    private String description;
    private String brand;
    private String petId;

    public Pet getPet() {
        return new Pet(getPrice(), getAmount(), getDescription(), getPetId());
    }

    public Shop getShop() {
        return new Shop(getBrand());
    }

    public Customer getCustomer() {
        return new Customer(getName(), getAddress());
    }

    private Address getAddress() {
        return new Address(getProvince(),
                    getCity(),
                    getArea(),
                    getStreet(),
                    getMoreDetails());
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getStreet() {
        return street;
    }

    public String getMoreDetails() {
        return moreDetails;
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

    public String getBrand() {
        return brand;
    }

    public String getPetId() {
        return petId;
    }
}
