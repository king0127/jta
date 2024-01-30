package com.jsoft.plgue.domain.resp;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Data
public class CommonMember {
    private static final Long serialVersionUID = 0L;

    @Column(name = "id")
    private Long id;

    /**
     * 会议ID(分表策略)
     */
    @Column(name = "meeting_id")
    private Long meetingId;

    /**
     * 成员openId
     */
    @Column(name = "member_id")
    private String memberId;

    /**
     * 成员飞书id（理想员工才有）
     */
    @Column(name = "member_feishu_id")
    private String memberFeishuId;

    /**
     * 成员姓名
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 成员头像
     */
    private String avatar;

    /**
     * 角色标识: Leader
     */
    @Column(name = "role_tag")
    private String roleTag;

    /**
     * 评审ID:范围[会议，议题，决策项，待办任务]
     */
    @Column(name = "object_id")
    private Long objectId;

    /**
     * 0-会议，1-议题，2-决策项，3-待办任务
     */
    @Column(name = "object_type")
    private Byte objectType;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    /**
     * 创建人的coa的staff id
     */
    @Column(name = "creator_staff_id")
    private String creatorStaffId;

    /**
     * 创建人的IDaaS的open id
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 修改时间
     */
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    /**
     * 修改人的coa的staff id
     */
    @Column(name = "updated_staff_id")
    private String updatedStaffId;

    /**
     * 修改人的IDaaS的open id
     */
    @Column(name = "updated_id")
    private String updatedId;

    /**
     * 是否删除，0-未删除，1-删除
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;
}