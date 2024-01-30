package com.jsoft.plgue.openfeign;

public class DynamicServiceCallBack implements DynamicService {
    @Override
    public Object executeParamPostApi(String url, Object params) {
        return null;
    }

    @Override
    public String executePostApi(String url) {

        System.out.println(" 请求异常，请联系管理员！ ");

        return null;
    }

    @Override
    public Object executeParamGetApi(String url, Object params) {
        return null;
    }

    @Override
    public Object executeGetApi(String url) {
        return null;
    }
}
