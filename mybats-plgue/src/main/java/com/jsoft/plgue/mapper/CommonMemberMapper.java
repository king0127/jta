package com.jsoft.plgue.mapper;

import com.jsoft.plgue.domain.resp.CommonMember;
import com.jsoft.plgue.mapper.provider.OrderMapperProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

//@Mapper
public interface CommonMemberMapper {

    @SelectProvider(method = "findMemberById", type = OrderMapperProvider.class)
    CommonMember findMemberById(@Param("meetingId") Long meetingId);

    @Insert({ "<script>", "insert into common_member (id, `meeting_id`, member_id, member_feishu_id, member_name, avatar, role_tag, object_id, object_type, created_at, creator_staff_id, creator_id, is_deleted) values",
            "<foreach collection='list' item='v' index='v' separator=','>", "(#{v.id} , #{v.meetingId}, #{v.memberId}, #{v.memberFeishuId}, #{v.memberName}, #{v.avatar}, #{v.roleTag}, #{v.objectId}, #{v.objectType}, #{v.createdAt}, #{v.creatorStaffId}, #{v.creatorId}, #{v.isDeleted})</foreach>", "</script>"})
    void save(@Param("list") List<CommonMember> list);
}
