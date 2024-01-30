package com.jsoft.plgue.aop;

import com.jsoft.plgue.annotation.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * redis锁切面： 处理并发情况下在事务中加锁问题
 */
@Aspect
@Slf4j
@Component
public class LogAspect {


    @Pointcut("@annotation(com.jsoft.plgue.annotation.LogAnnotation)")
    public void logCut() {

    }

    @Around("logCut()")
    public Object doLock(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        String name = logAnnotation.name();
        System.out.println(" 打印日志切面： -------------------------------  ");
        return joinPoint.proceed();
    }

}
