package com.daxue.pattern.singleton;

/**
 * 第三种(饿汉)
 * 这种方式基于classloder机制避免了多线程的同步问题，
 * 不过，instance在类装载时就实例化，
 * 这时候初始化instance显然没有达到lazy loading的效果。
 */
public class SingletonDemo3 {
    private static SingletonDemo3 instance = new SingletonDemo3();

    private SingletonDemo3() {}

    public static SingletonDemo3 getInstance() {
        return instance;
    }
}
