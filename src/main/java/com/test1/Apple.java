package com.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class Apple {

    public String color;

    public Integer weight;

    public Apple(String color) {
        this.color = color;
    }
    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return this.color;
    }

    public Integer getWeight() {
        return weight;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
    public static boolean isHeavyApple(Apple apple) {
        /**
         * 对于大于100,标示这是重的苹果
         */
        return apple.getWeight() > 100;
    }
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }



}
