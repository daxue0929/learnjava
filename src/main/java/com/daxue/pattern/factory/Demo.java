package com.daxue.pattern.factory;

/**
 * 提供一个创建对象实例的功能，而无须关心其具体实现
 */
public class Demo {
}


class Animal {
    public void eat(){
        
    }
}

class Dog extends Animal{
    @Override
    public void eat() {
        System.out.println("Dog 吃东西");
    }
}

class Cat extends Animal{
    @Override
    public void eat() {
        System.out.println("Cat 吃东西");
    }
}


