package com.example.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMain implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        return null;
    }


    public static void main(String[] args) {

//        Object instance = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{}, new TestMain());


        List<Integer> num = new ArrayList<>();
        for (int i = 0; i < 13485; i++) {
            num.add(i);
        }


//        num.stream().skip(200)

        int limit = countStep(num.size());


//        List<List<Integer>> collect = Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(a -> num.stream().skip(a * MAX_NUM).limit(MAX_NUM).parallel().collect(Collectors.toList())).collect(Collectors.toList());
//
//        System.out.println(collect.size());
//        System.out.println(limit);



    }
    static int MAX_NUM = 200;
    private static Integer countStep(Integer size) {
        return (size + MAX_NUM-1) / MAX_NUM;
    }
}
