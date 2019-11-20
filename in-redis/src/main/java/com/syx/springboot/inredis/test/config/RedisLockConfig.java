package com.syx.springboot.inredis.test.config;

import com.crossoverjie.distributed.constant.RedisToolsConstant;
import com.crossoverjie.distributed.lock.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;


/**
 * 分布式redis锁
 * https://github.com/crossoverJie/distributed-redis-tool
 *
 * @author syx
 * @date 11:50  2019/10/14
 * distributed-redis-tool
 */
//@Configuration
public class RedisLockConfig {
    private Logger logger = LoggerFactory.getLogger(RedisLockConfig.class);

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisLock build() {
        RedisLock redisLock = new RedisLock.Builder(jedisConnectionFactory, RedisToolsConstant.SINGLE)
                .lockPrefix("lock:")
                .sleepTime(100)
                .build();
        return redisLock;
    }
}
