package com.jsoft.plgue.controller;

import com.jsoft.plgue.Event.EventParam;
import com.jsoft.plgue.domain.resp.NumPO;
import com.jsoft.plgue.utils.SpringContext;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dy/bean")
public class DyBeanController {



    @GetMapping("/dyMethod")
    public Map<String, ? extends Object> dyMethod(Map<String, Class<?>> t) throws InstantiationException, IllegalAccessException {

        String beanName = "orderServiceImpl";

        Object bean = SpringContext.getBean(beanName);
        tests(new NumPO());

        Map<String, Class<?>> map = new HashMap<>();
        map.put("getDyNum", NumPO.class);
        map.put("topic", EventParam.TopicPO.class);
        map.put("a", List.class);
        Class<?> getDyNum = map.get("getDyNum");
        NumPO numPO = new NumPO();

        Map<String, ? extends Object> resMap = new HashMap<>();

        Method method = ReflectionUtils.findMethod(bean.getClass(), "getDyNum", getDyNum);

        Type genericReturnType = method.getGenericReturnType();
        String typeName = genericReturnType.getTypeName();


        Object result =  ReflectionUtils.invokeMethod(method, bean, numPO);
//        resMap.put("getDyNum", result);
        System.out.println(result);
        return null;
    }


    public <T> T tests(T t) {

        return t;
    }



    // ? 解析接受参数
    public static void test2(Map<String, ?> map) {


        for (Map.Entry m : map.entrySet()) {
            Object key = m.getKey();
            Object values = map.get(key);

            System.out.println("key:"+ key + " -------- val: " + values);
        }


    }


    public static void main(String[] args) {

        List<NumPO> numPOS = new ArrayList<>();
        numPOS.add(NumPO.builder().id(1).version(1).key("V").build());
        Map map = new HashMap();
        map.put("num", numPOS);
        map.put("str", "String");
        map.put("strBeanClass", NumPO.class);
        map.put("strBean", new NumPO());

        NumPO numPO = new NumPO();
        System.out.println(numPO.hashCode());
        Class<? extends NumPO> clazz = numPO.getClass();
        test2(map);
    }

}
