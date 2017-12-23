package com.thoughtworks.ddd.order.interfaces.common;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "data")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ApiForRequest<T> {
    private String type;
    private T attributes;

    public T getAttributes() {
        return attributes;
    }

    public void setAttributes(T attributes) {
        this.attributes = attributes;
    }
}
