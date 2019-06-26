package com.daxue.reflect.demo1;

public class Person {
    private String name;

    public int age;

    public Person() {
        System.out.println("默认的构造方法");
    }

    public Person(String name) {
        this.name = name;
        System.out.println("public Person(String name)");
    }

    private Person(int age) {
        this.age = age;
        System.out.println("private Person(int age)");
    }

    static
    {
        System.out.println("静态代码块");
    }
    public void eat(){
        System.out.println("吃东西啦");

    }

    public void sleep(String name,int age){
        System.out.println("public void sleep(String name,int age)");
    }
    public void sleep(String name){
        System.out.println("public void sleep(String name)");
    }
    private void smoke(int age){
        System.out.println("private void smoke(int age)");
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }



}
