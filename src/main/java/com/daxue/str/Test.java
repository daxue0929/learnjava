package com.daxue.str;

import java.util.Date;

public class Test {



    public static Boolean ValueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;

    }
    public static void main(String[] args) {

//        long time = new Date().getTime();
//        System.out.println(time);

        test03();

    }

    public static void test03() {
        String name = "dasda.png";
        int i = name.lastIndexOf(".");
        String result = name.substring(i+1);
        System.out.println(result);
    }
}
