package com.daxue.boot3.boot301demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Boot301DemoApplication {

    public static void main(String[] args) {
        var applicationContext =
            SpringApplication.run(Boot301DemoApplication.class, args);
        String[] names = applicationContext.getBeanDefinitionNames();

        System.out.println("===============================================");
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println("===============================================");
    }
}
