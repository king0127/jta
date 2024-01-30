package com.jsoft.plgue.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 重试注解
 */
@Component
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {

    /**
     * 重试次数，默认三次
     */
    int retryNum() default 3;
    /**
     * 重试时间间隔,单位毫秒
     */
    long executeInterval() default 0;
    /**
     * 指定重试异常
     */
    Class<? extends Throwable>[] retryFor() default {Exception.class};
    /**
     * 不进行重试的异常
     */
    Class<? extends Throwable>[] noRetryFor() default {};

}
