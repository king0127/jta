package com.example.redis.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCxt implements ApplicationContextAware {

    public static ApplicationContext cxt;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationCxt.cxt = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return ApplicationCxt.cxt;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> t) {
        return getApplicationContext().getBean(t);
    }

}
