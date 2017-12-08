package com.ThoughtWorks.DDD.Order.domain.order;

import com.ThoughtWorks.DDD.Order.domain.common.Entity;
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

    public Order() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean sameIdentityAs(Long otherId) {
        return this.id.equals(otherId);
    }
}
