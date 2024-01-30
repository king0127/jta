package com.example.redis.aspect;

import com.example.redis.annotation.MyLock;
import com.example.redis.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LockAsp {

    @Resource
    RedissonClient redissonClient;

    @Pointcut("@annotation(com.example.redis.annotation.MyLock)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object lock(ProceedingJoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyLock myLock = method.getAnnotation(MyLock.class);
        String name = myLock.name();
        String spEL = getSpEL(joinPoint, myLock.spEL());
        log.info(" get lock name -> {} ", name);
        RLock lock = redissonClient.getLock(name.concat(spEL));
        try {
            log.info(" 【加锁时间：{}】 ", LocalDateTime.now());
            lock.lock();
            return joinPoint.proceed();
        } catch (Exception e) {
            log.info(" 加锁失败，并获取一个错误：{} ", e.getMessage());
        } catch (Throwable e) {
            throw new ServiceException();
        } finally {
            lock.unlock();
            log.info(" 【锁释放时间：{}】 ", LocalDateTime.now());
        }
        return null;
    }


    private final DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    private final SpelExpressionParser parser = new SpelExpressionParser();

    private String getSpEL(ProceedingJoinPoint joinPoint, String el) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = nameDiscoverer.getParameterNames(method);
        if(parameterNames != null) {
            StandardEvaluationContext context = new StandardEvaluationContext();
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
            return parser.parseExpression(el).getValue(context, String.class);
        }
        return null;
    }

}
