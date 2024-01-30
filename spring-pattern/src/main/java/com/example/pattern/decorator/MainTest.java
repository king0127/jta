package com.example.pattern.decorator;

// 装饰器模式
public class MainTest {

    public static void main(String[] args) {

        /**
         *
         * 装饰器模式： 个人简单理解： 在原有完成的类的基础上，添加一个额外的操作。而且不破坏原类
         *
         * 装饰器的使用场景：
         *         java.io库
         *         mybatis源码 cache缓存
         *
         * 装饰器模式：
         *      接口： 该接口是统一的接口，是装饰器和被装饰器的基本类型
         *
         *      被装饰类： 原有功能的类
         *
         *      装饰类： 实现接口， 并使用有参构造方法初始化
         *
         *      装饰器产品类： 继承装饰类，重写装饰类的方法，并添加额外的功能处理。
         *
         */

        BMWCar bmwCar = new BMWCarColor(new CarImpl());

        bmwCar.carColor();

    }

}
