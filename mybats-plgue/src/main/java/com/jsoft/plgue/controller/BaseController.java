package com.jsoft.plgue.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

    static {
        System.out.println(" 静态代码---------- ");
    }


    public BaseController() {
        System.out.println(" 构造方法=========== ");
    }

    @ModelAttribute
    public void authCommon() {

        System.out.println("  ---------权限校验-------  ");
    }
}
