package com.example.pattern.template;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

public abstract class AbstractPhone {

    final void execute() {
        Long meetingId = null;
        doMake();
        updateMeetingStatus(meetingId);

        /**
         * 根据不同的状态处理不同的逻辑：
         * 1. 待评审，待上会状态： 创建评审结果； 更新会议状态
         * 2. 已上会，已评审状态 更新会议状态
         */

    }

    public abstract void doMake();

    void updateMeetingStatus(Long meetingId) {

        // 根据meetingId查询会议
        // 判断会议状态是否等于2 否则更新会议状态为2
        // if()

//        AtomicStampedReference atomicStampedReference = new AtomicStampedReference();


//        LongAdder

        AtomicLong atomicLong = new AtomicLong();
        atomicLong.compareAndSet(1245L, 1);

        System.out.println(atomicLong);


    }
}
