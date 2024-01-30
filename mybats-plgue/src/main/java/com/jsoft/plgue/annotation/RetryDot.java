package com.jsoft.plgue.annotation;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RetryDot {

    /**
     * 重试次数
     */
    @Value("${custom.property}")
    int retryTimes() default 3;
    /**
     * 重试时间间隔,单位毫秒
     */
    long executeInterval() default 0;
    /**
     * 为指定异常重试
     */
    Class<? extends Throwable>[] retryFor() default {Exception.class};
    /**
     * 排除的异常不进行重试,注意如果一个异常在retryFor和noRetryFor中同时指定,
     * 则不再进行重试
     */
    Class<? extends Throwable>[] noRetryFor() default {};
}
