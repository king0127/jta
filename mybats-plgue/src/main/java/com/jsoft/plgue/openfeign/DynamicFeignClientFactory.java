package com.jsoft.plgue.openfeign;

import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DynamicFeignClientFactory<T> {

    private FeignClientBuilder feignClientBuilder;

    public DynamicFeignClientFactory(ApplicationContext appContext) {
        this.feignClientBuilder = new FeignClientBuilder(appContext);
    }

    public T getFeignClient(final Class<T> type, String serviceId) {
        FeignClientBuilder.Builder<T> tBuilder = feignClientBuilder.forType(type, serviceId);
        tBuilder.fallback((Class<? extends T>) DynamicServiceCallBack.class);
        return tBuilder.build();
    }
}




//@Component
//public class FeignClientCustomBuilder implements ApplicationContextAware {
//
//    private static FeignClientBuilder builder;
//
//    /**
//     * 手动生成FeignClient,准备一个FeignClient基类，该类不用打{@link org.springframework.cloud.openfeign.FeignClient}注解
//     * @param clazz feignClient基类
//     * @param name
//     * @param contextId
//     * @param path
//     * @param <T>
//     * @return
//     */
//    public static <T> T getFeignClient(Class<T> clazz, String name, String contextId, String path) {
//        FeignClientBuilder.Builder<T> feignClientBuilder = builder.forType(clazz, name);
//        return feignClientBuilder.contextId(contextId).path(path).build();
//    }
//
//
//    /**
//     * 通过spring applicationContext生成builder
//     *
//     * @param applicationContext 实现ApplicationContextAware接口回调注入app
//     * @throws BeansException
//     */
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        builder = new FeignClientBuilder(applicationContext);
//    }
//}



