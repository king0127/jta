package com.jsoft.springsecurity.common;

import lombok.Getter;

@Getter
public enum EnumCode implements RespCode{


    SUCCESS(200, "成功"),
    FAILURE(201, "失败"),
    UNAUTHORIZED(401, "认证失败，请重新登录！")


    ;

    EnumCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
