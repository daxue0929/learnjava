package com.daxue.pattern.singleton;

/**
 * 第二种:(懒汉,线程安全)
 *      在getInstance()方法中加入了synchronized锁。能够在多线程中很好的工作，
 *      而且看起来它也具备很好的lazy loading，
 *      但是效率很低（因为锁），并且大多数情况下不需要同步
 *
 */
public class SingletonDemo2 {
    private static SingletonDemo2 instance;

    private SingletonDemo2() { }

    public static synchronized SingletonDemo2 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo2();
        }

        return instance;
    }
}
