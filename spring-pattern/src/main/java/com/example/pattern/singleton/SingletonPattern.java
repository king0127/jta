package com.example.pattern.singleton;

/**
 * 单例模式主要是保证创建的对象在jvm中是唯一的
 * 单例模式的三个特点：
 *  1. 私有构造
 *  2. 提供对外访问入口方法
 *  3. 持有自己类型的属性
 */
public class SingletonPattern {

    public static class SingletonInner{
        private static SingletonPattern singletonPattern = new SingletonPattern();
    }

    private SingletonPattern() {
    }

    public static SingletonPattern getSingleton() {
        return SingletonInner.singletonPattern;
    }

}
