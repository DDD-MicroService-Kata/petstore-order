package com.ThoughtWorks.DDD.Order.domain;

import java.io.Serializable;

public interface Entity<T> extends Serializable {
    T getId();

    boolean sameIdentityAs(T otherId);
}
