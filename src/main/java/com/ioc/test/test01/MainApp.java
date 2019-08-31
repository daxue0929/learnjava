package com.ioc.test.test01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("com/ioc/test/test01/Beans.xml");
        HelloWorld helloA = (HelloWorld) context.getBean("helloWorld");
        helloA.setMessage("I am a message!");

        helloA.getMessage();

        HelloWorld helloB = (HelloWorld) context.getBean("helloWorld");
        helloB.getMessage();

//        prototype是原型类型，它在我们创建容器的时候并没有实例化，而是在我们获取bean的时候才会去创建一个对象
//        二我们每次取到的对象都不是同一个对象，根据经验对有状态的bean应该使用prototype作用域，
//        而对无状态的bean应该使用singleton作用域。


    }
}
