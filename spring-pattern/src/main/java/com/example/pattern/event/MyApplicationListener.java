package com.example.pattern.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Slf4j
@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

    @Override
    public void onApplicationEvent(MyApplicationEvent event) {

        log.info(" 监听的事件参数：{} ", event);
        event.getMessage();
        EventSources eventSources = event.getEventSources();


        String className = eventSources.getClassName();
        String methodName = eventSources.getMethodName();
        Object bean = SpringContext.getBean(className);

        Method method = ReflectionUtils.findMethod(bean.getClass(), methodName, event.getDataSource().getClass());
        Object o = ReflectionUtils.invokeMethod(method, bean, event.getDataSource());

        // 定义一个通用的事件监听方法， 入参为议题状态表的ID,
        // 通过议题状态ID,关联查询获取到事件驱动的方法；
        // 然后驱动调用方法

        /**
         * 问题1. 驱动方法的入参数据，从事件触发来源搞，；
         * 例如： 创建评审结果，入参需
         */
    }
}
