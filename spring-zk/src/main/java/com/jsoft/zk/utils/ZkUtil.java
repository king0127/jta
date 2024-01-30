package com.jsoft.zk.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ZkUtil {

    public static final CuratorFramework click;

    static {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        click = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        click.start();
    }

    private ZkUtil() {

    }

    private static class SingletonHolder {
        private static final InterProcessMutex mutex = new InterProcessMutex(click, "/lock");
    }

    public static InterProcessMutex getMutex() {
        return SingletonHolder.mutex;
    }

    // 获取锁
    public static boolean getLock(long time) {
        try {
            return getMutex().acquire(time, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.info(" 获取锁异常：{} ", e.getMessage());
            return false;
        }
    }


    // 锁释放
    public static void release() {
        try {
            getMutex().release();
        }catch (Exception e) {
            log.info(" 释放锁异常：{} ", e.getMessage());
        }
    }


}
