package com.jsoft.plgue.aop;

import com.jsoft.plgue.annotation.RetryDot;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName RetryAspect
 */
@Aspect
@Component
@Slf4j
public class RetryAspect {

    ExecutorService executorService = new ThreadPoolExecutor(3, 5, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>());

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Value("${custom.property}")
    private int num;

    @Pointcut("@annotation(com.jsoft.plgue.annotation.RetryDot)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object tryAgain(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            RetryDot retry = AnnotationUtils.findAnnotation(method, RetryDot.class);
            if (null != retry) {
                int retryTimes = retry.retryTimes();
                retryTimes = num;
                Class<? extends Throwable>[] retryForExceptions = retry.retryFor();
                Class<? extends Throwable>[] excludeExceptions = retry.noRetryFor();
                long executeInterval = retry.executeInterval();

                if (retryTimes < 1
                        || isInstance(excludeExceptions, throwable)
                        || !isInstance(retryForExceptions, throwable)) {
                    throw throwable;
                }

                int currentRetryTimes = 1;
                while (true) {
                    log.info(" 接口失败重试中,第 {} 次重试...", currentRetryTimes++);
                    try {
                        if (executeInterval > 0) {
                            Thread.sleep(executeInterval);
                        }
                        return point.proceed();
                    } catch (Throwable e) {
                        if (currentRetryTimes > retryTimes) {
                            // 达到重试次数后，更新事件失败，并记录日志
                            // 问题： 这里怎么获取动态调用方法的参数信息， 通过ThreadLocal封住参数上下文吗？
                            throw e;
                        }
                    }
                }
            }
            throw throwable;
        }
    }

    private boolean isInstance(Class<? extends Throwable>[] array, Throwable e) {
        if (CollectionUtils.isEmpty(Arrays.asList(array)) || e == null) {
            return false;
        }
        for (Class<? extends Throwable> th : array) {
            if (th.isInstance(e)) {
                return true;
            }
        }
        return false;
    }

}