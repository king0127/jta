package com.jsoft.springsecurity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult implements Serializable {
    private static final long serialVersionUID = -8882085529150577122L;

    private Integer code;

    private String message;

    private Object result;


    public static class SingletonInner{
        private static final BaseResult singletonResult = new BaseResult();
    }

    public static BaseResult success() {
        BaseResult baseResult = getSingleton();
        baseResult.setCode(200);
        baseResult.setMessage("成功");
        return baseResult;
    }

    public static BaseResult success(Object result) {
        BaseResult baseResult = getSingleton();
        baseResult.setResult(result);
        baseResult.setCode(200);
        baseResult.setMessage("成功");
        return baseResult;
    }

    public static BaseResult success(EnumCode enumCode, Object result) {
        BaseResult singleton = getSingleton();
        singleton.setCode(enumCode.getCode());
        singleton.setMessage(enumCode.getMessage());
        singleton.setResult(result);
        return singleton;
    }

    public static BaseResult failure(EnumCode enumCode) {
        BaseResult singleton = getSingleton();
        singleton.setCode(enumCode.getCode());
        singleton.setMessage(enumCode.getMessage());
        return singleton;
    }

    public static BaseResult getSingleton() {
        return SingletonInner.singletonResult;
    }

}
