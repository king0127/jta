package com.example.pattern.decorator.mananger;

public abstract class AbstractHandler<T, R> implements BaseHandler<T, R> {

    protected BaseHandler baseHandler;

    public void setBaseHandler(BaseHandler baseHandler) {
        this.baseHandler = baseHandler;
    }

}
