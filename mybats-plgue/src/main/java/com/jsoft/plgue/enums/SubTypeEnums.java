package com.jsoft.plgue.enums;

import lombok.Getter;

// 分表类型枚举
public enum SubTypeEnums {

    SUFFIX("METING_SUFFIX"),
    SUFFIX_YEAR("YEAR_SUFFIX"),


    ;

    @Getter
    private String type;

    SubTypeEnums(String type) {
        this.type = type;
    }

}
