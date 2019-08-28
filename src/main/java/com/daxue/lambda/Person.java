package com.daxue.lambda;

import java.time.LocalDate;

public class Person {
    public enum Sex {
        MALE,FEMALE
    }
    String name;
    /**
     */
    public LocalDate birthday;

    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("Hello Lambda");
        r1.run();
    }

}
