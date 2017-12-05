package com.ThoughtWorks.DDD.Order.common;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    boolean sameValueAs(T other);
}
