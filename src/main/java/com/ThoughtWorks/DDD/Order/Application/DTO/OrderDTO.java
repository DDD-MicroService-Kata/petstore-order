package com.ThoughtWorks.DDD.Order.Application.DTO;

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
}
