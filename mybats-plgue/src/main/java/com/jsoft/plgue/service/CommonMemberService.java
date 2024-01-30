package com.jsoft.plgue.service;

import com.jsoft.plgue.domain.resp.CommonMember;

import java.util.List;

public interface CommonMemberService {


    void save(List<CommonMember> commonMember);


    CommonMember findMemberById(Long meetingId);


}
