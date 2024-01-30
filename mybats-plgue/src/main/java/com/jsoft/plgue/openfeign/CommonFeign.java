package com.jsoft.plgue.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

//@FeignClient(name = "spring-redis", url = "http://localhost:1100/")
@FeignClient(name = "spring-redis", fallback = DynamicServiceCallBack.class)
public interface CommonFeign {

//    http://localhost:1100/api/order/list

    @PostMapping("/api/order/list")
    String getList();
}
