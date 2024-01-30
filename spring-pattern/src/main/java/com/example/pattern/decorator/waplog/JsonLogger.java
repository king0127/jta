package com.example.pattern.decorator.waplog;

import jdk.internal.instrumentation.Logger;

public class JsonLogger extends WrapperLogger {

    public JsonLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void info(String s) {

        // 再写入日志之前对日志做处理
        super.info(s);
    }
}
