package com.ThoughtWorks.DDD.Order.domain.common;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    boolean sameValueAs(T other);
}
