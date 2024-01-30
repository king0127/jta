package com.jsoft.plgue.service.impl;

import com.jsoft.plgue.Event.EventParam;
import com.jsoft.plgue.annotation.RetryDot;
import com.jsoft.plgue.exception.BigException;
import com.jsoft.plgue.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TopicServiceImpl implements TopicService {


    @Override
    public void submitTopic() {

        log.info(" 提交议题 ");
    }

    @Override
    public String getMethod() {
        return "submitTopic";
    }

    @Override
    public void addResult(Integer param) {
        log.info(" 异步获取添加评审的参数：{}", Thread.currentThread().getId());
        throw new BigException("会议状态更新失败！");
    }

    @Override
    @RetryDot
    public void updateMeetingStatus(Integer num) {
        log.info(" 同步更新会议线程信息：{} ", Thread.currentThread().getId());
        throw new BigException("会议状态更新失败！");
    }

    @Override
    @RetryDot
    public void test(Integer num) {
        log.info(" 异步打印测试线程信息：{} ", Thread.currentThread().getId());
        throw new BigException("逻辑异常！");
    }

}
