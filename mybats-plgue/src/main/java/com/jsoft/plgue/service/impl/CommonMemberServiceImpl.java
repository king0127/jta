package com.jsoft.plgue.service.impl;

import com.jsoft.plgue.domain.resp.CommonMember;
import com.jsoft.plgue.mapper.CommonMemberMapper;
import com.jsoft.plgue.service.CommonMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CommonMemberServiceImpl implements CommonMemberService {

    @Resource
    private CommonMemberMapper commonMemberMapper;

    @Override
    public void save(List<CommonMember> commonMember) {

        commonMemberMapper.save(commonMember);
    }

    @Override
    public CommonMember findMemberById(Long meetingId) {

        return commonMemberMapper.findMemberById(meetingId);
    }
}
