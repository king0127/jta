package com.jsoft.plgue.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestParam implements EventContext {

    // 会议ID
    private Long meetingId;

    // 议题ID
    private Long topicId;

    @Override
    public TestParam makeData() {
        return TestParam.builder().meetingId(1L)
                .topicId(3L).build();
    }
}
