package com.daxue.pattern.singleton;

/**
 * 确保一个类只有一个实例，并且自行实例化，并向整个系统提供这个能力
 * <p>
 * 优点：
 * 1、单例模式在内存中只有一个实例，减少了内存开支，特别是一个对象需要频繁地创建、销毁时，且创建或销毁时性能又无法优化，单例模式的优势就很明显。
 * 2、由于单例模式只生成一个实例，所以减少了系统的开销，当一个对象的产生需要比较多的资源时，如读取配置，产生其他的依赖对时则可以通过正在应用启动时产生一个单例对象，然后永久驻留在内存的方式解决。
 */

/**
 * 第一种(懒汉,线程不安全)
 *      这种写法lazy loading很明显，但是致命的是在多线程不能正常工作。　
 */
public class SingletonDemo1 {
    private static SingletonDemo1 instance;

    private SingletonDemo1() {
    }

    public static SingletonDemo1 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo1();
        }
        return instance;
    }


}
