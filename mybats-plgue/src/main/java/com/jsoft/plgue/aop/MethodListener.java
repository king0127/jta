//package com.jsoft.plgue.aop;
//
//import com.jsoft.plgue.service.TopicService;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
///**
// * 动态aop切面： https://blog.csdn.net/yh4494/article/details/126637765
// */
//@Slf4j
//@Aspect
//@Component
//public class MethodListener {
//
//    /**
//     * 处理查询数据库监听方法是否被调用
//     *
//     */
//
//    @Resource
//    private TopicService topicService;
//    String dynamicPointcutExpression="";
//
//    @PostConstruct
//    public void updateDynamicPointcutExpression() {
//        String methodName = topicService.getMethod();
//        dynamicPointcutExpression = "execution(* com.jsoft.plgue.service.impl.TopicServiceImpl" + methodName + "(..))";
//    }
//
//    @Pointcut(value= "")
//    public void methodCut() {
//
//    }
//
//    @Before("methodCut()")
//    public void beforeMethod() {
//        log.info(" 方法调用之前 ");
//    }
//
//    @After("methodCut()")
//    public void afterMethod() {
//        log.info(" 方法调用之后 ");
//    }
//
//
//}
