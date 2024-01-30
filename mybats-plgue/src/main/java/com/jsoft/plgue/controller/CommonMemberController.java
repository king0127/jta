package com.jsoft.plgue.controller;

import com.jsoft.plgue.domain.resp.CommonMember;
import com.jsoft.plgue.service.CommonMemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/member")
public class CommonMemberController {


    @Resource
    private CommonMemberService commonMemberService;

    @GetMapping("/save")
    public void save() {

        List<CommonMember> commonMembers = new ArrayList<>();
        for (int i = 100; i < 110; i++) {
            CommonMember commonMember = new CommonMember();
            commonMember.setMemberId("1");
            commonMember.setId(i+1L);
            commonMember.setMeetingId(i+0L);
            commonMember.setMemberName("测试"+i);
            commonMember.setAvatar("头像"+i);
            commonMember.setMemberFeishuId("1245");
            commonMember.setCreatedAt(OffsetDateTime.now());
            commonMember.setCreatorId("1245");
            commonMember.setIsDeleted((byte) 0);
            commonMember.setObjectId(i + 12L);
            commonMember.setObjectType((byte) 0);
            commonMembers.add(commonMember);
        }
        commonMemberService.save(commonMembers);

        System.out.println("新增成功");

    }

    @GetMapping("/find")
    public void findMemberById() {
        CommonMember memberById = commonMemberService.findMemberById(1241L);
        System.out.println(memberById);
    }


}
