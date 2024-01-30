package com.jsoft.plgue.Event;

public class ContextManager<T> {
    private ThreadLocal<T> context = new ThreadLocal<>();
    public void setContext(T value) {
        context.set(value);
    }
    public T getContext() {
        return context.get();
    }

    public void clear() {
        context.remove();
    }
}
