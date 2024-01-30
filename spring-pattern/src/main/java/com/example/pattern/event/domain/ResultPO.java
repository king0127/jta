package com.example.pattern.event.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultPO {

    /**
     * 评审会议id
     */
    private Long meetingId;

    /**
     * 议题id
     */
    private Long topicId;

    /**
     * 决议结果
     */
    private Integer resolutionConfirm;

    /**
     * 反馈结果
     */
    private String resultCommend;

    /**
     * 模块配置ID
     */
    private Long moduleConfigId;
}
