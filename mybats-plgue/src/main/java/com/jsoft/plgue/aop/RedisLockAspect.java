package com.jsoft.plgue.aop;

import com.jsoft.plgue.annotation.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * redis锁切面： 处理并发情况下在事务中加锁问题
 */
@Aspect
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RedisLockAspect {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private com.jsoft.plgue.utils.RedisLock redissonLock;

    private final long timeout = 10;

    @Pointcut("@annotation(com.jsoft.plgue.annotation.RedisLock)")
    public void lockCut() {

    }

    @Around("lockCut()")
    public Object doLock(ProceedingJoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String name = redisLock.name();
        Boolean success = redisTemplate.opsForValue().setIfAbsent(name, name, timeout, TimeUnit.SECONDS);
        try {
            if(success) {
                redissonLock.renewExpiration(name, name, timeout);
                return joinPoint.proceed();
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            redissonLock.releaseLock(name, name);
        }
        return null;
    }

}
