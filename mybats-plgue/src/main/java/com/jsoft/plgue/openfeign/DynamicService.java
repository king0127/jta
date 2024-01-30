package com.jsoft.plgue.openfeign;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface DynamicService {

    /**
     * 避坑记录： 1. context-type： application/json
     * 2. 返回数据类型必须一直，否则会数据格式转换失败，无法得到返回结果
     * 3. todo: 这种通用的feign配置相应的callback熔断异常处理【还有点小问题】
     */
    @PostMapping(value = "{url}", produces = "application/json;charset=utf-8")
    Object executeParamPostApi(@PathVariable("url") String url, @RequestBody Object params);

    @PostMapping(value = "{url}", produces = "application/json;charset=utf-8")
    Object executePostApi(@PathVariable("url") String url);

    @GetMapping(value = "{url}", produces = "application/json;charset=utf-8")
    Object executeParamGetApi(@PathVariable("url") String url, @SpringQueryMap Object params);

    @GetMapping(value = "{url}", produces = "application/json;charset=utf-8")
    Object executeGetApi(@PathVariable("url") String url);

}
