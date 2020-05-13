package com.commerceApp.commerceApp.util.responseDtos;

import java.io.Serializable;

public class BaseDto implements Serializable {
    public static final long serialVersionUID=1L;
    private String message;

    public BaseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "message='" + message + '\'' +
                '}';
    }
}
