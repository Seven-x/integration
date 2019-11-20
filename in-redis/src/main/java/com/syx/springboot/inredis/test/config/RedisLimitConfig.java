package com.syx.springboot.inredis.test.config;

import com.crossoverjie.distributed.constant.RedisToolsConstant;
import com.crossoverjie.distributed.limit.RedisLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * 分布式限流
 * https://github.com/crossoverJie/distributed-redis-tool
 *
 * @author syx
 * @date 13:52  2019/10/14
 */
//@ComponentScan(value = "com.crossoverjie.distributed.intercept")
//@Configuration
public class RedisLimitConfig {
    private Logger logger = LoggerFactory.getLogger(RedisLimitConfig.class);

    @Value("${redis.limit}")
    private int limit;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisLimit build() {
        RedisLimit redisLimit = new RedisLimit.Builder(jedisConnectionFactory, RedisToolsConstant.SINGLE)
                .limit(limit)
                .build();
        return redisLimit;
    }
}
