package com.daxue.pattern.singleton;

/**
 * 第四种(饿汉,变种)
 *      表面上看起来差别挺大，其实更第三种方式差不多，都是在类初始化即实例化instance
 */
public class SingletonDemo4 {

    private static SingletonDemo4 instance = null;

    static {
        instance = new SingletonDemo4();
    }

    private SingletonDemo4() { }

    private SingletonDemo4 getInstance() {
        return instance;
    }
}
