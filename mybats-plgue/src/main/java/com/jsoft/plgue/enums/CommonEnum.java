package com.jsoft.plgue.enums;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

public enum CommonEnum {

    WMS("WMS"),
    MES("MES"),
    DMS("DMS"),

    ;

    @Getter
    private String name;


    CommonEnum(String name) {
        this.name = name;
    }

    private static Set<String> nameList = new HashSet<>(CommonEnum.values().length);

    public static Set<String> initList() {
        if(CollectionUtils.isEmpty(nameList)) {
            for (CommonEnum commonEnum : CommonEnum.values()) {
                nameList.add(commonEnum.name);
            }
        }
        return nameList;
    }
}
