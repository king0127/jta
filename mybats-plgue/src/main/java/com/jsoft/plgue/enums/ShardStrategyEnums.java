package com.jsoft.plgue.enums;

import lombok.Getter;

public enum ShardStrategyEnums {

    LAST_POSITION("LAST_POSITION"),

    ;
    @Getter
    private final String name;

    ShardStrategyEnums(String name) {
        this.name = name;
    }

}
