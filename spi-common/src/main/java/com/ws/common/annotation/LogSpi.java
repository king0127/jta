package com.ws.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义redis锁注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogSpi {

    /**
     * 名称
     * @return
     */
    String name();


}
