package com.example.pattern.decorator.file;

import org.springframework.stereotype.Service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Service
public @interface Decorate {
    /**
     * 具体的业务场景
     * @return
     */
    String scene();
    /**
     * 类型:不同业务场景下，不同的装饰器类型
     * @return
     */
    String type();
}
