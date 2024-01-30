package com.example.redis.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MyLock {

    String name() default "";

    String spEL() default "redis::key";
}
