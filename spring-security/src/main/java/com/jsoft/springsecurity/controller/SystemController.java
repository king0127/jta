package com.jsoft.springsecurity.controller;

import com.jsoft.springsecurity.common.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system")
public class SystemController {

    @GetMapping("/list")
    public BaseResult queryList() {
        return BaseResult.success("获取系统列表");
    }

}
