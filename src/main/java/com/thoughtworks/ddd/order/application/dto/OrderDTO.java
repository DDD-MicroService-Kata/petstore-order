package com.thoughtworks.ddd.order.application.dto;

import com.thoughtworks.ddd.order.domain.order.Address;
import com.thoughtworks.ddd.order.domain.order.Customer;
import com.thoughtworks.ddd.order.domain.order.Pet;
import com.thoughtworks.ddd.order.domain.order.Shop;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setMoreDetails(String moreDetails) {
        this.moreDetails = moreDetails;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }
}
