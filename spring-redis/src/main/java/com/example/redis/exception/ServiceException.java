package com.example.redis.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private Integer code;

    private String message;

    public ServiceException() {
        super();
    }

    public ServiceException(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

}
