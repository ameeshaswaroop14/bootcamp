package com.commerceApp.commerceApp.util.responseDtos;

public class ObjectDto<Object> extends BaseDto {
    private Object object;

    public ObjectDto(String message, Object object) {
        super(message);
        this.object = object;
    }
}
