package com.example.redis.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ThreadPoolConfig {

    public Executor poolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 20, 50, TimeUnit.MILLISECONDS
                ,new LinkedBlockingDeque<>(), new ThreadPoolExecutor.AbortPolicy()
        );
        return executor;
    }

}
