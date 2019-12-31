package com.syx.springboot.inutils.test.stack;

import java.util.Arrays;

/**
 * 模拟栈
 *
 * @author shaoyx
 * @date 9:47  2019/12/30
 */
public class MyStack {
    /**
     * 存储栈中元素
     */
    private int[] storage;
    /**
     * 栈的容量
     */
    private int capacity;
    /**
     * 栈中元数素量
     */
    private int count;

    private static final int GROW_FACTORY = 2;

    public MyStack() {
        capacity = 8;
        storage = new int[8];
        count = 0;
    }

    public MyStack(int initialCapacity){
        if (initialCapacity < 1){
            throw new IllegalArgumentException("initialCapacity is small");
        }
        capacity = initialCapacity;
        storage = new int[initialCapacity];
        count = 0;
    }

    // TODO: 2019/12/30 入栈
    public void push(int value){
        if (count == capacity){
            ensureCapacity();
        }
        storage[count++] = value;
    }

    // TODO: 2019/12/30 确保容量大小
    public void ensureCapacity(){
        int newCapacity = capacity * GROW_FACTORY;
        storage = Arrays.copyOf(storage, newCapacity);
        capacity = newCapacity;
    }

    // TODO: 2019/12/30 返回栈顶元素并出栈
    public int pop(){
        count--;
        if (count == -1){
            throw new IllegalArgumentException("MyStack is empty");
        }
        return storage[count];
    }

    // TODO: 2019/12/30 返回栈顶元素不出栈
    public int peek(){
        if (count == 0){
            throw new IllegalArgumentException("MyStack is empty");
        }
        return storage[count-1];
    }

    // TODO: 2019/12/30 判断栈是否为空
    public boolean isEmpty(){
        return count == 0;
    }

    // TODO: 2019/12/30 返回栈中元素的个数
    public int size(){
        return count;
    }
}
