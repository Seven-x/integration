package com.syx.springboot.inredis.test.redis;

import com.crossoverjie.distributed.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * 分布式锁
 * @author syx
 * @date 13:42  2019/10/14
 *
 */
public class DistributedLock {

    @Autowired
    private RedisLock redisLock;

    public void nonBlockingLock() {
        String key = "key";
        String request = UUID.randomUUID().toString();

        try {
            boolean lock = redisLock.tryLock(key, request);
            if (!lock) {
                throw new Exception();
            }

            // do something

        } catch (Exception e) {

        } finally {
            redisLock.unlock(key, request);
        }

    }

    public void blockingLock() {
        String key = "key";
        String request = UUID.randomUUID().toString();
        int blockTime = 1;
        try {
            redisLock.lock(key, request);
            redisLock.lock(key, request, blockTime);
            // do something

        } catch (Exception e) {

        } finally {
            redisLock.unlock(key, request);
        }
    }
}
