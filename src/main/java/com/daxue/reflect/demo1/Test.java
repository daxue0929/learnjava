package com.daxue.reflect.demo1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //获取字节码文件对象

        //1.通过对象获取
//        Class c1 = new Person().getClass();
//        System.out.println(c1);

        //2.通过类型获取
//        Class c2 = Person.class;
//        System.out.println(c2);
//        System.out.println("两个对象是否相等"+(c1 == c2));

        //获取类中所有public的构造方法数组
//        new Test().method01();


//        new Test().method2();

    }

    public void method01() throws ClassNotFoundException{
        //获取字节码文件对象
        Class c = Person.class;

        //获取所有公共的构造方法数组
        Constructor[] constructors = c.getConstructors();
        for (Constructor item : constructors) {
            System.out.println(item);
        }
    }
    public void method2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = Person.class;

        //获取空参的构造方法
        Constructor constructor = c.getConstructor();

        Object obj = constructor.newInstance();//调用构造方法创建对象

        //向下转型
        Person person = (Person) obj;
        person.eat();

        Constructor constructor1 = c.getConstructor(String.class);
        Person person1 = (Person) constructor1.newInstance("王三");
        System.out.println(person1);


    }
}
