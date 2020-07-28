package com.daxue.str;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

    public int id;

    private static Set<String> aa  = new HashSet<String>();//存贮结果

    public static Boolean ValueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;

    }
    public static void main(String[] args) {

//        long time = new Date().getTime();
//        System.out.println(time);

//        test03();
//        test04();
//        boolean aba = repeatedSubstringPattern("aba");
//        System.out.println(aba);

//        test05();

//        test06();
//        test07();

//        test08();

            test09();

    }


    public static void test09() {
        Integer i = ("88" + "17344615861").hashCode();
        HashSet<Integer> set = new HashSet<>();
        set.add(i);
        if (set.contains(i)) {
            System.out.println("存在");
        }

    }


    public static void test08() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("a");
        jsonArray.add("b");

        JSONObject obj = new JSONObject();

        obj.put("name", jsonArray);

        System.out.println(obj.toString());

    }



    public static void test07() {
        String[] arrays = new String[]{"1", "2", "3", "4"};
        String join = StringUtils.join(arrays, "+");
        System.out.println(join);
    }


    public static boolean repeatedSubstringPattern(String s) {
        // 获取一个字符串的全部子串
        split(s);
        for (String str : aa) {
            String result = s.replaceAll(str + "{1}", "");
            if (result.length() == 0){
                return true;
            }
        }
        return false;
    }

    public static void split(String a){
        String sub1 = "1";
        String sub2 = "2";
        if (a.length()==1) {
            aa.add(a);
        }else{
            for(int i =1;i<a.length();i++){
                sub1 = a.substring(i);
                sub2 = a.substring(0,i);
                aa.add(sub1);
                aa.add(sub2);
                split(sub1);
                split(sub2);
            }
        }
    }

    public static void test05() {
        System.out.println(System.currentTimeMillis());
    }

    public static void test06() {
        Test test = null;

        String txtAccount = "489a7s8d7as8da|12456|sd,124865d54as4df5as4f5asd";
        Integer id = null;

        String s = String.valueOf(test.id);

        int i = txtAccount.indexOf(s);

        System.out.println(i);


    }



    public static void test03() {
        String name = "dasda.png";
        int i = name.lastIndexOf(".");
        String result = name.substring(i+1);
        System.out.println(result);
    }
    public static void test04() {
        String resp = "{\"msg\":\"成功\",\"code\":\"T\",\"data\":{\"mms_id\":\"60865\",\"status\":\"R\"}}";

        JSONObject jsonObject = JSONObject.parseObject(resp);

        /**
         * 粗心大意的测试,应为忘记返回了return
         */

        JSONObject response = JSON.parseObject(resp);
        String code = response.getString("code");
        String msg = response.getString("msg");
        JSONObject data = response.getJSONObject("data");
        if ("T".equals(code)) {
            String mmsId = data.getString("mms_id");
            String status = data.getString("status");
            if ("R".equals(status)) {
                System.out.println(mmsId);
            }else if ("T".equals(status)) {
                System.out.println(mmsId);
            }
        }
        System.out.println(msg);
    }

}
