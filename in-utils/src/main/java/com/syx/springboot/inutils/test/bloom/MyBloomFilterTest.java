package com.syx.springboot.inutils.test.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import javafx.scene.effect.Bloom;

/**
 * @author shaoyx
 * @date 16:54  2020/1/3
 */
public class MyBloomFilterTest {

    public static void main(String[] args) {
        String val1 = "111112222223333333";
        String val2 = "222222222222222222";
        MyBloomFilter bloomFilter = new MyBloomFilter();
        System.out.println(bloomFilter.contains(val1));
        System.out.println(bloomFilter.contains(val2));
        bloomFilter.add(val1);
        bloomFilter.add(val2);
        System.out.println(bloomFilter.contains(val1));
        System.out.println(bloomFilter.contains(val2));

        // guava 中的 bloom filter
        BloomFilter<Integer> bloom = BloomFilter.create(
                Funnels.integerFunnel(), 1500, 0.01);
        System.out.println(bloom.mightContain(1));
        System.out.println(bloom.mightContain(2));
        bloom.put(1);
        bloom.put(2);
        System.out.println(bloom.mightContain(1));
        System.out.println(bloom.mightContain(2));

    }

}
