package com.test1;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;

import java.util.Date;

public class Test1002 {
    public static void main(String[] args) {

//        Date date = new Date(new Date().getTime() - 7200000);
//
//        System.out.println("1111111");

//        JSONObject object = new JSONObject();
//        object.put("a", "1");
//        object.put("b", "2");
//        object.put("c", 10);
//
//        String s = object.toJSONString();
//
//        String s1 = JSONObject.toJSONString(s);
//        System.out.println(s1);


//        String text = "aaaa.jpeg";
//        String substring = text.substring(text.lastIndexOf(".")+1);
//        System.out.println(substring);
//
//        long time = new Date().getTime();
//        System.out.println(time);

        String s = DigestUtils.md5DigestAsHex("Ydzssdsp123".getBytes()).toLowerCase();
        System.out.println(s);

    }

}
