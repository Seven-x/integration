integration - in-transaction
#in-transaction

#### 引入maven
```
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

#### 基础知识
- 编码式事务处理：基于编码实现
- 声明式事务处理：基于AOP
>- 配置文件：xml事务规则声明
>- 注解：@Transactional

#### @Transactional 实现
使用@Transactional注解，在方法上、类上

#### Attribute Description ： 属性描述
    name	        | 选择指定的事务管理器(在存在多事务管理器时使用：TransactionManager)。
    propagation 	| 事务传播行为，默认为REQUIRED。已有事务则加入，否则重新创建一个。
    isolation	    | 事务隔离级别，默认为DEFAULT。
    timeout 	    | 事务超时时间，默认为-1， 如果超时未完成则回滚事务。
    read-only	    | 只读事务，默认为false，无需事务处理是 可设置read-only=true。
    rollback-for	| 事务回滚的异常类型，如果多个则逗号隔开。需继承自RuntimeException 的异常类
    no-rollback-for	| 抛出指定的异常类型，不回滚。

#### Propagation：事务传播行为
    REQUIRED        	| 支持事务。存在则加入，不存在则新建	
    REQUIRES_NEW 	    | 支持事务。存在则挂起当前事务，不存在则新建	
    NESTED      	    | 支持事务。存在则嵌套成一个子事务，不存在则新建	
    SUPPORTS 	        | 支持事务。存在则支持，不存在则不管他	            | 不回滚
    NOT_SUPPORTED 	    | 不支持事务。存在则挂起，执行完后则恢复	            | 不回滚
    MONDATORY           | 支持事务。存在则加入，不存在则抛出异常	
    NEVER	            | 不支持事务。存在则抛出异常	                        | 不回滚

#### Isolation：事务隔离级别

