package com.daxue.str;

import org.junit.jupiter.api.Test;

import java.io.File;

public class SearchStr {
    public static void main(String[] args) {

    }


    @Test
    public void test(){
        String NaviURL = "http://sdvrcdn.shidongvr.com/ccgavnavi/40535376/tour.html";
        int subIndex = NaviURL.indexOf("ccgavnavi/");
        
        System.out.println(subIndex);

        System.out.println( NaviURL.toCharArray()[subIndex+10]);

        String child = NaviURL.substring(subIndex + 10, NaviURL.length() - 10);
        System.out.println(child);

    }

    @Test
    public void test001(){
        String path = "C:\\\\server\\data\\ccga";
        File file = new File(path);

        System.out.println(file.exists());

    }

    @Test
    public void test002(){
        String os = System.getProperty("os.name");
        System.out.println(os.toLowerCase().startsWith("win"));
    }

    @Test
    public void test004(){

        

    }
}








