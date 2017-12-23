package com.thoughtworks.ddd.order.domain.common;

import java.io.Serializable;

public interface Entity<T> extends Serializable {
    T getId();

    boolean sameIdentityAs(T otherId);
}
