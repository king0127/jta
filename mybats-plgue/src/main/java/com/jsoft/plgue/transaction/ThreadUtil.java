package com.jsoft.plgue.transaction;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadUtil {

    public static void main(String[] args) {

        Thread thread = new Thread();
        thread.start();
        thread.run();



    }

}


class CallUtils implements Callable {

    @Override
    public Object call() throws Exception {
        return null;
    }

}


class RunUtil implements Runnable{

    @Override
    public void run() {

    }
}
