package com.example.pattern.decorator.waplog;

import jdk.internal.instrumentation.Logger;

public class WrapperLogger implements Logger {

    public Logger logger;

    public WrapperLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void error(String s) {

    }

    @Override
    public void warn(String s) {

    }

    @Override
    public void info(String s) {
        System.out.println(" 打印info日志 ");
    }

    @Override
    public void debug(String s) {

    }

    @Override
    public void trace(String s) {

    }

    @Override
    public void error(String s, Throwable throwable) {

    }
}
