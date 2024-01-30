package com.example.pattern.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventSources {

    private Long id;

    private Integer topicId;

    private String name;

    // 议题状态： 0-草稿；1-待评审；2-已评审
    private Integer status;

    // 是否触发事件
    private boolean isEvent;

    // 触发事件类
    private String className;

    // 触发事件方法名称
    private String methodName;
}
