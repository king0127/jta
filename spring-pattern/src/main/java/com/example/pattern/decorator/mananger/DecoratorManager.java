package com.example.pattern.decorator.mananger;


import com.example.pattern.decorator.file.Decorate;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecoratorManager {

    /**
     * 用于存放装饰器类
     */
    private Map<String, AbstractHandler> decorateHandleMap = new HashMap<>();

    /**
     * 将具体装饰器类放在map中
     *
     * @param handlerList
     */
    public void setDecorateHandler(List<AbstractHandler> handlerList) {
        for (AbstractHandler h : handlerList) {
            Decorate annotation = AnnotationUtils.findAnnotation(h.getClass(), Decorate.class);
            decorateHandleMap.put(createKey(annotation.scene(), annotation.type()), h);
        }
    }

    /**
     * 返回具体的装饰器类
     *
     * @param type
     * @return
     */
    public AbstractHandler selectHandler(String scene, String type) {
        String key = createKey(scene, type);
        return decorateHandleMap.get(key);
    }

    /**
     * 拼接map的key
     * @param scene
     * @param type
     * @return
     */
    private String createKey(String scene, String type) {
        StringBuilder StrUtil = new StringBuilder();
        return StrUtil.append(scene).append(":").append(type).toString();
    }


}
