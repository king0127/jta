package com.example.pattern.guancha;

import java.util.Vector;

/**
 * 简单理解： 这个就是消息的生产和投递，将消息存放在队列中，并给与一个消息投递方法;
 * 其实就是将数据存放一份备用， 当存放比较多时候比较占用内存，何时才能释放内存也是一个问题；
 */
public abstract class Subject {

    private String name;

    private Vector<Observer> observers = new Vector<>();

    // 添加观察
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // 移除观察
    public void delObserver(Observer observer) {
        if(!observers.isEmpty() && observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    // 通知
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public abstract void doMsg();
}
