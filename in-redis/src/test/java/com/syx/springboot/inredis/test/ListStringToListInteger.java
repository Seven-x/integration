package com.syx.springboot.inredis.test;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * List<String> -> List<Integer>
 *
 * @author syx
 * @date 9:47  2019/10/23
 */
public class ListStringToListInteger {

    /**
     * 普通循环转换
     */
    public static Consumer<List<String>> forConversion() {
        return list -> {
            List<Integer> integers = Lists.newArrayList();
            for (String str : list) {
                integers.add(Integer.valueOf(str));
            }
        };
    }

    /**
     * lambda mapToInt循环转换
     * @author syx
     */
    public static Consumer<List<String>> lambdaConversion() {
        return list -> Lists.newArrayList(list.stream()
                .mapToInt(Integer::valueOf)
                .iterator());
    }

    /**
     * lambda map循环转换
     * @author syx
     */
    public static Consumer<List<String>> lambdaConversion1() {
        return list -> list.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    /**
     * guava iterables transform循环转换
     *
     * @author syx
     */
    public static Consumer<List<String>> iterablesConversion() {
        return list ->
                Lists.newArrayList(Iterables.transform(list, new Function<String, Integer>() {
                    @NullableDecl
                    @Override
                    public Integer apply(@NullableDecl String input) {
                        return Integer.valueOf(input);
                    }
                }));
    }

    /**
     * iterators transform循环转换
     *
     * @author syx
     */
    public static Consumer<List<String>> iteratorsConversion() {
        return list -> Lists.newArrayList(Iterators.transform(list.iterator(), new Function<String, Integer>() {
            @NullableDecl
            @Override
            public Integer apply(@NullableDecl String input) {
                return Integer.valueOf(input);
            }
        }));
    }


    /**
     * transform 循环转换
     *
     * @author syx
     */
    public static Consumer<List<String>> transformConversion() {
        return list -> Lists.transform(list, e -> {return Integer.valueOf(e);});
    }

    public static void test(Consumer<List<String>> consumer, List<String> list, String msg) {
        long start = System.currentTimeMillis();
        consumer.accept(list);
        long end = System.currentTimeMillis();
        System.out.println(msg + " need time:" + (end - start));
    }

    @Test
    public void listStringToIntegerTest(){
        List<String> list = org.assertj.core.util.Lists.newArrayList();
        Integer len = 100;
        for (int i = 0; i < len; i++) {
            list.add(i + "");
        }

        test(forConversion(), list, "普通for循环 forConversion");
        test(lambdaConversion(), list, "Stream mapToInt lambdaConversion");
        test(lambdaConversion1(), list, "Stream map Conversion1");
        test(iterablesConversion(), list, "Guava iterables.transform Conversion");
        test(iteratorsConversion(), list, "Guava iterators.transform Conversion");
        test(transformConversion(), list, "Guava Lists.transform Conversion");
    }

}
