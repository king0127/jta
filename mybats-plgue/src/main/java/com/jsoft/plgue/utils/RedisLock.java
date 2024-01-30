package com.jsoft.plgue.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisLock {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 10,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
    /**
     * 加锁
     *
     * @param key
     * @param userId
     * @param timeOut
     * @return
     */
    public boolean lock(String key, String userId, long timeOut) {
        boolean success = redisTemplate.opsForValue().setIfAbsent(key, userId, timeOut,
                TimeUnit.SECONDS);
        if (!success) {
            log.error("key:{},userId:{} get lock failer ", key, userId);
            return false;
        }
        try {
            renewExpiration(key,userId, timeOut);
            // 这里执行业务逻辑
//            Thread.sleep(20000);
            return true;
        } catch (Exception e) {
            log.error("lock occur error:{}", e);
            return false;
        } finally {
            releaseLock(key, userId);
        }
    }
    /**
     * 续命操作
     *
     * @param key
     * @param timeOut
     */
    public void renewExpiration(String key,String userId, long timeOut) {
        threadPool.execute(() -> {
            Long currentTimeMills = System.currentTimeMillis();
            System.out.println("开始:" + LocalDateTime.now() + " ,timeOut:" + timeOut);
            while (true) {
                if (redisTemplate.hasKey(key) &&
                        userId.equals(redisTemplate.opsForValue().get(key))) {
                    Long cost = System.currentTimeMillis() - currentTimeMills;
                    if (cost > timeOut * 1000 / 3) {
                        currentTimeMills = System.currentTimeMillis();
                        redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
                        System.out.println("进行中:" + LocalDateTime.now() + " ,timeOut:" +
                                timeOut);
                    }
                } else {
                    break;
                }
            }
        });
    }
    /**
     * 释放锁
     *
     * @param key
     * @param userId
     */
    public void releaseLock(String key, String userId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript(script, Long.class);
        Long result2 = redisTemplate.execute(redisScript, Arrays.asList(key), userId);
        System.out.println("result2=" + result2);
    }
}
