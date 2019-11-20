package com.syx.springboot.inredis.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.gson.JsonObject;
import org.assertj.core.util.Lists;
import org.json.JSONObject;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author syx
 * @date 9:35  2019/10/14
 * 测试字符串拼接效率（占位符）
 * 1.+
 * 2.String.concat()
 * 3.String.format()
 * 4.MessageFormat.format()
 * 5.StringBuilder.append()
 * 6.stream()
 * 7.StringJoiner.add()
 * 8.Guava
 */
public class SpliceStringTest {

    // +
    public static Consumer<List<String>> connectTest() {
        return (list) -> {
            String s = "format: ";
            for (String str : list) {
                s += str;
            }
        };
    }

    // concat()
    public static Consumer<List<String>> concatTest() {
        return (list) -> {
            String s = "format: ";
            for (String str : list) {
                s.concat(str);
            }
        };
    }

    // String.format()
    public static Consumer<List<String>> sFormatTest() {
        return (list) -> {
            for (String str : list) {
                String.format("format: %s", str);
            }
        };
    }

    // MessageFormat.format()
    public static Consumer<List<String>> mFormatTest() {
        return (list) -> {
            for (String str : list) {
                MessageFormat.format("format: %s", str);
            }

        };
    }

    // StringBuilder.append()
    public static Consumer<List<String>> appendTest() {
        return (list) -> {
            StringBuilder sb = new StringBuilder("format: ");
            for (String str : list) {
                sb.append(str);
            }
            sb.toString();
        };
    }

    // Stream
    public static Consumer<List<String>> streamTest() {
        return (list) -> {
            list.stream().collect(Collectors.joining(","));
        };
    }

    // StringJoiner.add()
    public static Consumer<List<String>> stringJoinerTest() {
        return (list) -> {
            StringJoiner joiner = new StringJoiner(",");
            for (String str : list) {
                joiner.add(str);
            }
            joiner.toString();
        };
    }

    // Guava
    public static Consumer<List<String>> guavaTest() {
        return (list) -> {
            Joiner.on(",").join(list);
        };
    }

    public static Consumer<String> splitter(){
        return str -> {
            List<String> list = Lists.newArrayList();
            Iterable iterable = Splitter.on(',').trimResults().split(str);
            for (Object o : iterable){
                list.add(String.valueOf(o));
            }
        };
    }

    public static Consumer<String> splitter1(){
        return str -> {
            List<String> list = Lists.newArrayList();
            for (String s : Splitter.on(',').trimResults().split(str)){
                list.add(String.valueOf(s));
            }
        };
    }


    public static void test(Consumer<List<String>> consumer, List<String> list, String msg, Integer len) {
        long start = System.currentTimeMillis();
        consumer.accept(list);
        long end = System.currentTimeMillis();
        System.out.println(msg + " need time:" + (end - start));
    }

    public static void splitter(Consumer<String> consumer, String str, String msg) {
        long start = System.currentTimeMillis();
        consumer.accept(str);
        long end = System.currentTimeMillis();
        System.out.println(msg + " need time:" + (end - start));
    }

    @Test
    public void spliceStringTest() {
        List<String> list = Lists.newArrayList();
        Integer len = 100000;
        for (int i = 0; i < len; i++) {
            list.add("list:" + i);
        }

//        test(connectTest(), list, "connectTest", len);
        test(concatTest(), list, "concatTest", len);
        test(sFormatTest(), list, "sFormatTest", len);
        test(mFormatTest(), list, "mFormatTest", len);
        test(appendTest(), list, "appendTest", len);

        System.out.println();
        test(streamTest(), list, "streamTest", len);
        test(stringJoinerTest(), list, "stringJoinerTest", len);
        test(guavaTest(), list, "guavaTest", len);
    }


    @Test
    public void test1(){
        String str = "123,123,3234,435,5,6,7777,7";
        Iterator iterator = Splitter
                .on(',')
                .trimResults()
                .split(str).iterator();
        List<String> list = Lists.newArrayList();
       while (iterator.hasNext()){
           list.add(String.valueOf(iterator.next()));
       }
        System.out.println(list);
    }

    @Test
    public void test2(){
        String str = "123,123,3234,435,5,6,7777,7";
        splitter(splitter(), str, "先转Object");
        splitter(splitter1(), str, "直接转String");
    }


    @Test
    public void test3(){
        String str = "{\"code\":\"8573\"} " ;
//        Map<String, String> map = JsonObject
//        System.out.println(map);
    }

}
