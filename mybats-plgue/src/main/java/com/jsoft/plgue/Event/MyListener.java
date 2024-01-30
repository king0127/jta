package com.jsoft.plgue.Event;

import com.jsoft.plgue.exception.BigException;
import com.jsoft.plgue.utils.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * 事件监听器
 */
@Slf4j
@Component
public class MyListener implements ApplicationListener<MyEvent> {

    @Resource
    private AsyncTaskExecutor executor;

    @Resource
    private ThreadPoolTaskExecutor asyncTaskExecutor;

    @Override
//    @RetryDot
    public void onApplicationEvent(MyEvent myEvent) {
        Integer dictId = myEvent.getDictId();
        List<ConfigPO> configPOList = new ArrayList<>();
        ConfigPO configPO = ConfigPO.builder().dictId(1).clazz("topicServiceImpl").method("addResult").type(2).build();
        ConfigPO configPO2 = ConfigPO.builder().dictId(1).clazz("topicServiceImpl").method("updateMeetingStatus").type(1).build();
        ConfigPO configPO3 = ConfigPO.builder().dictId(1).clazz("topicServiceImpl").method("test").type(2).build();
        configPOList.add(configPO);
        configPOList.add(configPO2);
        configPOList.add(configPO3);
        List<ConfigPO> collect = configPOList.stream().filter(res -> dictId.equals(res.getDictId())).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(collect)) {
            long count = collect.stream().filter(res -> res.getType() == 2).count();
            CountDownLatch downLatch = new CountDownLatch((int) count);
            for (int i = 0; i < collect.size(); i++) {
                boolean flag2 = true;
                ConfigPO res = collect.get(i);
                Integer type = res.getType();
                try {
                    String className = res.getClazz();
                    String methodName = res.getMethod();
                    Object bean = SpringContext.getBean(className);
                    Object data = getData(myEvent.getData(), methodName);
                    log.info(" 打印线程：{} ", Thread.currentThread().getId());

                    if(type == 2) {
//                        CompletableFuture.allOf((CompletableFuture<?>) CompletableFuture.supplyAsync(() -> {
//                            Method method = ReflectionUtils.findMethod(bean.getClass(), methodName, data.getClass());
//                            assert method != null;
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
//                            return ReflectionUtils.invokeMethod(method, bean, data);
//                        }, executor).get()).join();

                        asyncTaskExecutor.submit(() -> {
                            boolean asyFlag = true;
                            try {
                                Method method = ReflectionUtils.findMethod(bean.getClass(), methodName, data.getClass());
                                assert method != null;
                                ReflectionUtils.invokeMethod(method, bean, data);
                            } catch (Exception e) {
                                log.error(" 执行驱动异常，异常线程名称：{}, 异步调用的方法名称：{}  ", Thread.currentThread().getName(), methodName);
                                // 更新事件状态失败
                                log.info(" catch 异步更新事件状态, 方法 {}", res.getMethod());
                                asyFlag = false; // 更新异步事件状态为失败
                            } finally {
                                downLatch.countDown();
                                if(asyFlag) {
                                    // 更新异步事件状态为成功
                                }
                            }
                        });

                    } else {
                        Method method = ReflectionUtils.findMethod(bean.getClass(), methodName, data.getClass());
                        assert method != null;
                        Object invoke = ReflectionUtils.invokeMethod(method, bean, data);
                    }
                } catch (Exception e) {
                    if (e instanceof ExecutionException) {
                        log.info(" 异常线程ID:{} ", Thread.currentThread().getId());
                        log.info(" catch 异步更新事件状态, 方法 {}", res.getMethod());
                        /**
                         * 异常吞并之后，更新事件状态，
                         */
                        flag2 = false;
                    } else if(e instanceof BigException) {
                        log.info(" catch  同步更新事件状态， 调用方法{} ", res.getMethod());
                        /**
                         * 异常吞并之后，更新事件状态，
                         */
                        flag2 = false;
                    }

                } finally {
                    // 更新事件状态
                    if(flag2 && type==1) {
                        log.info(" finally 更新事件状态, 调用方法{} ", res.getMethod());
                    }
                }
            }

            try {
                downLatch.await();
            } catch (InterruptedException e) {
                log.info(" 异步线程异常：{} ", e);
                e.printStackTrace();
            }


            log.info(" 通过发布者查询事件状态，更新发布者状态 ");
        }
    }


    /**
     * 判断驱动调用的方法，获取对应的数据参数体
     */
    public Object getData(Object data, String methodName) {
        Class<?>[] declaredClasses = data.getClass().getDeclaredClasses();
        if(declaredClasses.length > 1) {
            try {
                Class<?> aClass = data.getClass();
                Field field = aClass.getDeclaredField(methodName);
                field.setAccessible(true);
                return field.get(data);
            } catch (Exception e) {
                log.error(" 动态获取属性失败，该属性数据不存在，请核对信息！ ");
            }
        }
        return data;
    }
}
