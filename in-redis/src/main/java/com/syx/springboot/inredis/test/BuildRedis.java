package com.syx.springboot.inredis.test;

import redis.clients.jedis.Jedis;

import java.util.*;

public class BuildRedis {
    public static void main(String[] args){
        // 链接本地redis服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("链接成功");

        // 存储字符串
        jedis.set("syx","123");
        System.out.println(jedis.get("syx"));

        // 存储list
        jedis.lpush("syx-list", "123");
        jedis.lpush("syx-list", "234");
        jedis.lpush("syx-list", "345");
        List<String> list = jedis.lrange("syx-list",0,10);
        for (String s:list){
            System.out.println(s);
        }

        // 存储hash
        Map<String, String> map = new HashMap<>();
        map.put("1","123");
        map.put("2","234");
        map.put("3","345");
        jedis.hmset("syx-hash",map);
        Map<String,String> mapRes = jedis.hgetAll("syx-hash");


        // 存储set
        jedis.sadd("syx-set","set1");
        jedis.sadd("syx-set","set2");
        System.out.println(jedis.smembers("syx-set"));

        // 存储zset
        jedis.zadd("syx-zset",0,"set1");
        jedis.zadd("syx-zset",0,"set2");
        jedis.zadd("syx-zset",0,"set3");
        System.out.println(jedis.zrangeByScore("syx-zset",0,10));

    }
}
