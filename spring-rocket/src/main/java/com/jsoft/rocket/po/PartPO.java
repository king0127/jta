package com.jsoft.rocket.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartPO {

    /**
     * 变换类型
     */
    private String exchangeType;

    /**
     * 任务类型
     */
    private String[] taskType;

    /**
     * 修改前零件名称
     */
    private String beforePartName;


    /**
     * 修改前零件编码
     */
    private String beforePartCode;

    /**
     * 修改后零件名称
     */
    private String afterPartName;

    /**
     * 修改后零件编码
     */
    private String afterPartCode;

    /**
     * 是否重新可靠性验证
     */
    private String isReliabilityVerify;

    /**
     * 任务模板集合
     */
    private List<Long> taskLibIds;

}
