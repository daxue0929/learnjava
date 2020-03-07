package com.daxue.lambda;

import java.time.LocalDate;

/**
 * @author Admin
 */
public class Person {
    public enum Sex {
        MALE,FEMALE
    }
    String name;
    /**
     */
    public LocalDate birthday;

    public Person() {
        name = "daxue";
    }



    public int add(int a, int b) {
        return a + b;
    }

    public String printName (){
        return name;

    }


    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("Hello Lambda");
        r1.run();
    }
}
