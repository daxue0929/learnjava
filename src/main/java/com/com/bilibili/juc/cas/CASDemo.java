package com.com.bilibili.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author daxue0929
 * @date 2022/10/25
 */

public class CASDemo {
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);

        //第一个参数是预期值，第二个参数是想要更新的值
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());


    }
}
