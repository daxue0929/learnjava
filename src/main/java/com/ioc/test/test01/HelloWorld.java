package com.ioc.test.test01;

public class HelloWorld {

    private String message;

    public void setMessage(String message){
        this.message  = message;
    }

    public void getMessage(){
        System.out.println("Your Message : " + message);
    }


    public static void main(String[] args) {
        System.out.println("在编写代码时注意关键字的大小写，严格执行规范中的约束，例如：1、类名称：所有单词的首字母大写；2、方法名：第一个单词的首字母小写，其余单词首字母大写；3、\\static属性：所有字母大写，可用‘-’连接，例如：\\VALUE_OF_MATH");
    }
}
