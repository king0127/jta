package com.jsoft.plgue.service;

import com.jsoft.plgue.Event.EventParam;
import com.jsoft.plgue.Event.TestParam;

public interface TopicService {

    void submitTopic();


    String getMethod();

    void addResult(Integer num);

    void updateMeetingStatus(Integer num);

    void test(Integer num);


}
