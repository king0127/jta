package com.example.pattern.singleton;

import java.util.stream.IntStream;


/**
 * 单例模式：
 *  单例模式有多种方式
 *      1. 懒汉式
 *      2. 饿汉式
 *      3. 双锁
 *      4. 静态内部类(这个作为成熟，常用该类)
 *      5. ThreadLocal实现的同一个线程内部单例模式： 来源于 mybatis源码中ErrorContext就是采用ThreadLocal方式实现的单例模式
 *          该方式我已经亲自验证过， 相同的线程中使用的单例模式：
 *          好处： 因为ThreadLocal是本地线程共享变量，意思是在一个线程链路上，任何时候，任何地方均可从ThreadLocal中获取变量信息
 *
 *          这里还有一点对ThreadLocal的见解： 既然它是本地线程共享变量，那么针对多线程中事务的问题，我们是否可以将事务的SqlSession存放在ThreadLocal中传递下去
 *
 */
public class SingletonTest {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            SingletonPattern singleton1 = SingletonPattern.getSingleton();
//            System.out.println(singleton1.hashCode());
//        }


        ThSingleton thSingleton = ThSingleton.getThSingleton();
        thSingleton.setCode(1);
        thSingleton.setMsg("测试");

        System.out.println(thSingleton.hashCode());


        System.out.println(ThSingleton.getThSingleton().hashCode());


        IntStream.range(0, 5).forEach(v -> new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(ThSingleton.getThSingleton().hashCode());
        }));



    }

}
