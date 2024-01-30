package com.jsoft.plgue.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(String var, Class<T> c2) {
        return context.getBean(var, c2);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public ApplicationContext SpringContext() {
        return SpringContext.context;
    }


    public static Object getBean(String name) {
        return context.getBean(name);
    }


    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static Object getType(String name) {
        Class<?> type = context.getType(name);
        return context.getType(name);
    }

    // 判断bean是否在ioc容器中
    public boolean containsBean(String name) {

        return context.containsBean(name);
    }

}
