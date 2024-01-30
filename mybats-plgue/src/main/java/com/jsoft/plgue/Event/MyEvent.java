package com.jsoft.plgue.Event;

import org.springframework.context.ApplicationEvent;

public class MyEvent<T> extends ApplicationEvent {


    private Integer dictId;
    private T data;

    public MyEvent(Integer dictId, T source) {
        super(source);
        this.dictId = dictId;
        this.data = source;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }
}
