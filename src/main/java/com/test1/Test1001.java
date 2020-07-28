package com.test1;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

public class Test1001 {

    public static void main(String[] args) {
        test();
    }

    private static void test() {

        String[] ids;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "wangxuedi");
        JSONArray array = new JSONArray();
        array.add("001");
        array.add("002");
        jsonObject.put("ids", array);

        Test1001.User user = JSONObject.parseObject(jsonObject.toJSONString(), Test1001.User.class);
        System.out.println(user.name);
        System.out.println(user.ids);

    }
    @Data
    public static class User {
        private String name;
        private String[] ids;
    }
}
