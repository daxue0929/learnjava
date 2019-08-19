package com.daxue.pattern.singleton;

/**
 * 不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象，可谓是很坚强的壁垒啊，
 * 不过，个人认为由于1.5中才加入enum特性，
 * 用这种方式写不免让人感觉生疏，在实际工作中，我也很少看见有人这么写过
 */
public enum SingletonDemo6 {
    instance;
    public void whateverMethod() {
    }
}
