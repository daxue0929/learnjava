package com.test1;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daxue.model.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
public class AppleTestMain {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("green", 10));
        inventory.add(new Apple("red", 20));
        inventory.add(new Apple("green", 300));
        inventory.add(new Apple("red", 600));
        inventory.add(new Apple("green", 500));
        List<Apple> greenAppleApples = Apple.filterApples(inventory, Apple::isGreenApple);
        List<Apple> HeavyApples = Apple.filterApples(inventory, Apple::isHeavyApple);

    }
}
