package com.jsoft.plgue.controller;

import com.jsoft.plgue.Event.*;
import com.jsoft.plgue.service.TopicService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Resource
    private TopicService topicService;

    @Resource
    private ApplicationEventPublisher publisher;

    @Resource
    private ApplicationContext applicationContext;


    @GetMapping("/submit/{dictId}")
    public void submitTopic(@PathVariable("dictId") Integer dictId) {

//        MyEvent<EventParam> event = new MyEvent<>(dictId, EventParam.builder().topicId(1L).moduleConfigId(3).meetingId(1L).build());
//        MyEvent<EventParam> event = new MyEvent<>(dictId, new EventParam().makeData());
        MyEvent<Integer> event2 = new MyEvent<>(dictId, 1);
//        applicationContext.publishEvent(event);
        applicationContext.publishEvent(event2);
    }


}
