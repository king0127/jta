package com.jsoft.zk.domain;

import lombok.Getter;

public enum TenantEnum {

//    PPVS("PPVS"),

    ;
    @Getter
    private String name;

    TenantEnum(String name) {
        this.name = name;
    }
}
