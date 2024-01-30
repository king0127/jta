package com.example.pattern.event;

import com.example.pattern.event.domain.ResultPO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ControllerTest {


    @Resource
    private ApplicationContext applicationContext;


    @GetMapping("/test")
    public void test() {
        ResultPO resultPO = ResultPO.builder()
                .topicId(1L).meetingId(2323424523452345234L).resultCommend("测试数据").moduleConfigId(3L).resolutionConfirm(0)
                .build();

        EventSources eventSources = EventSources.builder()
                .isEvent(true).topicId(1).methodName("createResult").className("driveConfig").name("测试").status(1)
                .build();
        MyApplicationEvent<ResultPO> event = new MyApplicationEvent("测试数据");
        event.setDataSource(resultPO);
        event.setEventSources(eventSources);
        applicationContext.publishEvent(event);
    }



}
