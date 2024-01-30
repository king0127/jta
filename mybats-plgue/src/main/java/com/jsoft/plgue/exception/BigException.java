package com.jsoft.plgue.exception;

import lombok.Data;

@Data
public class BigException extends RuntimeException {

    private int code;

    private String msg;

    BigException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BigException() {
        super();
    }

    public BigException(String msg) {
        super();
        this.msg = msg;
    }
}
