package com.jsoft.plgue.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventParam implements EventContext {

    // topicDiscussion.getModuleConfigId(), meetingId, topicId
    /**
     * 将所有需要的字段全部封装在一起，通过泛型T接受参数，将驱动的方法入参全部都用这个实体类接受
     */
    private TopicPO addResult;

    private Task updateMeetingStatus;

    private Integer meetingStatus;

    @Override
    public EventParam makeData() {

        TopicPO build = TopicPO.builder().meetingId(1L)
                .topicId(3L)
                .meetingStatus(0)
                .moduleConfigId(1)
                .build();
        Task task1 = Task.builder().id(1L).taskStatus(1).orderNum(2L).build();
        return EventParam.builder().addResult(build).updateMeetingStatus(task1).meetingStatus(4).build();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TopicPO {
        // 评审模块ID
        private Integer moduleConfigId;

        // 会议ID
        private Long meetingId;

        // 议题ID
        private Long topicId;

        // 会议状态
        private Integer meetingStatus;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Task {
        /**
         * 租户
         */
        private Long id;

        /**
         * 排序
         */
        private Long orderNum;

        /**
         * 任务状态
         */
        private Integer taskStatus;
    }
}
