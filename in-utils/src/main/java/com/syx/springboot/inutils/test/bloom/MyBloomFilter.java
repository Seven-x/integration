package com.syx.springboot.inutils.test.bloom;

import java.util.BitSet;

/**
 * bloom filter 手动实现
 * https://github.com/Snailclimb/JavaGuide/blob/master/docs/dataStructures-algorithms/data-structure/bloom-filter.md
 *
 * @author shaoyx
 * @date 16:26  2020/1/3
 */
public class MyBloomFilter {

    /**
     * 位数组默认大小 2 * 2^24
     */
    private static final int DEFAULT_SIZE = 2 << 24;

    /**
     * 通过这个数组可以创建不同的hash函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    /**
     * 位数组 数组中存的元素只能是 0 或 1
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /**
     * 存放包含 hash 函数的类的数组
     */
    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    /**
     * 初始化多个包含 hash 函数的类的数组，每个类中的 hash 函数都不一样
     */
    public MyBloomFilter() {
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组
     *
     * @param value
     * @return
     */
    public void add(Object value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    /**
     * 判断指定元素是否存在于位数组
     *
     * @param value
     * @return
     */
    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    /**
     * 静态内部类 用于 hash 操作
     */
    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 计算 hash 值
         */
        public int hash(Object value) {
            int hash;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((hash = value.hashCode()) ^ (hash >>> 16)));
        }
    }
}
