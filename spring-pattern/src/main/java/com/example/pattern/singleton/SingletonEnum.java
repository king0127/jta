package com.example.pattern.singleton;

import lombok.Getter;

public enum SingletonEnum {

    SINGLETON("")
    ;

    SingletonEnum(String msg) {
        this.msg = msg;
    }
    @Getter
    private String msg;

}
