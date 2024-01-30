package com.jsoft.es.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPO implements Serializable {
    private static final long serialVersionUID = 4679079952395060525L;
    /**
     * 成员id
     */
    private String memberId;

    /**
     * 成员姓名
     */
    private String memberName;

    /**
     * 成员类型 0-员工 1-供应商
     */
    private Integer memberType;

    /**
     * 1-负责人
     */
    private Integer authRole;

    /**
     * 角色标识DRE/PTC/VSE
     */
    private String bizRoleTag;

    /**
     * 角色
     */
    private String bizRoleName;

    /**
     * 所属对象0-任务 1-零件计划 2-专业模块 3-整车计划
     */
    private String objectType;

    /**
     * 所属对象id
     */
    private Long objectId;

    private String avatar;


    private String memberFeiShuId;




}
