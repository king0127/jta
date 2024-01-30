package com.jsoft.plgue;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------------------- come in");

            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ------------------- 被唤醒");
        }, "t1");
        t1.start();

    }
}
