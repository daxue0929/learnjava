package com.daxue.theadDemo.callMainMethodMainThread;

public class Test {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
    }


    public void test01() {
        new Runnable() {
            @Override
            public void run() {

            }
        };
    }
}
