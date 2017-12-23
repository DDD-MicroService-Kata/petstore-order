package com.thoughtworks.ddd.order.interfaces.common;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "data")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ApiForResponse<T> {
    public ApiForResponse() {
    }

    private Long id;
    private T attributes;

    public ApiForResponse(Long id, T attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public Long getId() {
        return id;
    }

    public T getAttributes() {
        return attributes;
    }
}
