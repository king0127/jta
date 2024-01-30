package com.example.pattern.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class MyApplicationEvent<T> extends ApplicationEvent {

    // 消息
    private String message;

    private EventSources eventSources;

    private T dataSource;

    public T getDataSource() {
        return dataSource;
    }

    public void setDataSource(T dataSource) {
        this.dataSource = dataSource;
    }

    public EventSources getEventSources() {
        return eventSources;
    }

    public void setEventSources(EventSources eventSources) {
        this.eventSources = eventSources;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyApplicationEvent(Object source) {
        super(source);
    }

    public MyApplicationEvent(Object source, String message, T t) {
        super(source);
        this.message = message;
        this.dataSource = t;
    }

    public MyApplicationEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
