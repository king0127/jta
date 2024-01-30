package com.sdk.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class UserController {


    @GetMapping("/user/info")
    public String getUserInfo() {

        return "用户基础信息";
    }

}
