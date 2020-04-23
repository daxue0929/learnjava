package com.practice.example;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author Admin
 * @date 2020/03/21
 **/
public class TypeConversion {
    public static void main(String[] args){
        byte b = 127;
        char c = 'W';
        short s = 23561;
        int i = 3333;
        long l = 400000L;
        float f = 3.14159F;
        double d = 54.523;

        // 低类型向高类型自动转换
        System.out.println("累加byte等于:" + b);
        System.out.println("累加char等于:" + (b+c));

        Scanner cin = new Scanner(System.in);
        System.out.println("请输入一个数字:");
        long number = cin.nextLong();
        String check = (number % 2 == 0) ? "这个数字是偶数" : "这个数字是奇数";
        System.out.println(check);


        BigDecimal bigDecimal = new BigDecimal("10000");
        BigDecimal decimal = new BigDecimal("100");

        BigDecimal result = bigDecimal.add(decimal);
        System.out.println(result);

    }
}
