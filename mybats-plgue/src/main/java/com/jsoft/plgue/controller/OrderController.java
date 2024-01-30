package com.jsoft.plgue.controller;

import com.jsoft.plgue.exception.BigException;
import com.jsoft.plgue.service.OrderService;
import com.jsoft.plgue.utils.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController extends BaseController {

    @Resource
    private OrderService orderService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ThreadPoolTaskExecutor asyncTaskExecutor;

    @GetMapping("/dyFunc")
    public String getDyFunc() throws Exception {
//        Object orderServiceImpl = getProxyClazz("com.jsoft.plgue.service.impl.OrderServiceImpl", "orderServiceImpl");
        // orderServiceImpl.getClass().getDeclaredMethod("getDyFunc", )

        if(true) {
            throw new BigException("测试");
        }

//        String clazzName = "com.jsoft.plgue.service.impl.OrderServiceImpl";
//        // 把所有的方法全部写在一个指定的类中，::
//        Class<?> c2 =  Class.forName(clazzName);
//
//        System.out.println(c2.getName());
//        String[] split = clazzName.split("\\.");
//        String clazz = split[split.length-1];
//        String s = clazz.substring(0, 1).toLowerCase(Locale.ROOT).concat(clazz.substring(1));
//        Object obj = SpringContext.getBean(s, c2);

        String clazzName = "orderServiceImpl";
        Object obj = SpringContext.getBean("orderServiceImpl");

        Object type = SpringContext.getType(clazzName);

        System.out.println(type);

        log.info("obj [ {} ] " ,obj);
        Object invoke = obj.getClass().getDeclaredMethod("getDyFunc", String.class, String.class).invoke(obj, "zs1", "研发评审会议");
        System.out.println(invoke);
        return "成功";
    }

    // 分两种情况： 第一bean注册到ioc容器中， 通过spring上下文获取bean类
    // 第二种： bean没注册到spring中， 需要通过反射获取到类信息，并执行类中的方法
    private <T> T getProxyClazz(String packageName, String clazzName) {
        try {
            Class<T> clazz = (Class<T>) Class.forName(packageName);
            T bean = SpringContext.getBean(clazzName, clazz);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/byId")
    public String queryListById() {

        orderService.queryListById(1);
        return "成功";
    }

    @GetMapping("/saveNum")
    public String saveNum() {

//        orderService.saveNum();
        log.info(" 信息保存 ");
        asyncTaskExecutor.submit(() -> {
            log.info(" 异步执行打印日志 ");
            orderService.queryListById(1);
        });

        return "成功";
    }

    @GetMapping("/getNumList")
    public String getNumList() throws InstantiationException, IllegalAccessException {
        orderService.getNumList();
        return "成功";
    }

    @GetMapping("/update/{name}")
    public String updateById(String name) {
        orderService.updateById(name);
        return "成功";
    }


    @GetMapping("/num")
    public void getNum() throws InterruptedException {

        String value = UUID.randomUUID().toString();
        orderService.getNum(value);
    }


    @GetMapping("/key")
    public String getKey() {

        redisTemplate.opsForValue().set("username2", "测试2222sdasdvaasdad_____sasdasd+asdfasdf%4654651@#￥%……&*（）——+");
        Object o = redisTemplate.opsForValue().get("username2");
        log.info(" 获取redis中数据{} ", o);
        return null;
    }

}
