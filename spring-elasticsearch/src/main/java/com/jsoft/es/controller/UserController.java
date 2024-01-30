package com.jsoft.es.controller;

import com.jsoft.es.domain.UserPO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController {



    @Resource
    private MongoTemplate mongoTemplate;


    @GetMapping("/save")
    private void saveUser() {
        UserPO userPO = UserPO.builder()
                .memberId("12200")
                .avatar("2323")
                .bizRoleName("管理员")
                .authRole(1)
                .memberFeiShuId("2323sw")
                .memberName("Tom")
                .objectId(2323L)
                .objectType("schedule")
                .memberType(1)
                .bizRoleTag("ptc")
                .build();
        mongoTemplate.insert(userPO);
    }


}
