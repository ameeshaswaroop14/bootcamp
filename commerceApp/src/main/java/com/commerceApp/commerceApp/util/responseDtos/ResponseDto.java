package com.commerceApp.commerceApp.util.responseDtos;

public class ResponseDto<T> extends BaseDto {
    private T data;

    public ResponseDto(String message, T data) {
        super(message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

