package com.example.pattern.template;

import sun.misc.Unsafe;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TemplateBoot {
    public static void main(String[] args) throws Exception {

        /**
         * 模板方法：
         *  1. 抽象类为主。 抽象类定义执行的流程顺序，抽象类的子类实现具体的个性化信息，
         *  差异化通过子类实现；
         *  子类是否执行，通过钩子函数识别是否执行
         *
         *  缺点：当子类越多，抽象模板方法的判断可能会增加，抽象类不好维护
         */

        Map<String, Integer> map = null;



        AtomicLong atomicLong = new AtomicLong();
        atomicLong.compareAndSet(1245L, 1);

        System.out.println(atomicLong);

        Unsafe unsafe = Unsafe.getUnsafe();

        try {
            long value = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));




        } catch (Exception ex) {
            throw new Exception(ex);
        }


    }
}
