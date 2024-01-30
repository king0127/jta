package com.jsoft.plgue.annotation;

import java.lang.annotation.*;

/**
 * 自定义redis锁注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    /**
     * 名称
     * @return
     */
    String name();


}
