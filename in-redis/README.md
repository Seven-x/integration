in-redis 整合redis
##架构
Jedis：Redis的Java实现客户端，提供了比较全面的Redis命令的支持
       阻塞IO、不支持异步、线程不安全

Redisson：实现了分布式和可扩展的java数据结构(分布式锁、分布式集合、延迟队列)
        基于Netty框架的事件驱动的通信层、异步、线程安全
        
Lettuce：高级Redis客户端(线程安全同步、异步和响应使用、集群、Sentinel、管道和编码器)
        基于Netty框架的事件驱动的通信层、异步、线程安全
##redis
自动配置：RedisAutoConfiguration
    自动生成redisTemplate和stringRedisTemplate

手动配置：RedisConfig
    获取RedisTemplate<String,Object>
 
序列化方式：
    String序列化
    JDK序列化

####连接池



