package com.jsoft.plgue.controller;

import com.jsoft.plgue.domain.req.UserBuilder;
import com.jsoft.plgue.domain.req.UserParam;
import com.jsoft.plgue.service.PartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class PartController {


    @Resource
    private PartService partService;

    @PostMapping("/part/update")
    public void updatePart(@RequestBody UserParam param) {

        UserParam userParam = param.saveBuilder(param);
        System.out.println(userParam);
    }


}
