package com.daxue.str;



import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());

        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(timeZone);
        System.out.println(instance);
//1570444789183

        System.out.println(instance.getTimeInMillis());


    }

    @Test
    public void test005() {
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
        String str = sdf.format(cd.getTime());

        System.out.println(str);
        System.out.println(cd.getTimeInMillis());

        System.out.println("----------");

        System.out.println("------------");
        System.out.println(System.currentTimeMillis());


    }

    @Test
    public void test006() {
        long l = System.currentTimeMillis();

        long mill = l / 1000;  //获取总秒数量

        long currentSecond = mill % 60;  //获取当前秒

        long min = mill / 60;

        long hour = min / 60;

        long day = hour / 24;

        long year = day / 360+ 1970;
        System.out.println(l);
        System.out.println();
        System.out.println(year);

        System.out.println("--------------");
        System.out.println(new Date().getTime()/1000);
    }
}








