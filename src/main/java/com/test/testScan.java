package com.test;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

public class testScan {
    public static void main(String[] args) {

//        String str = System.getProperty("aa");
//        String bb = System.getProperty("bb");
//
//        Long aLong = Long.getLong("aa");
//        Long bb1 = Long.getLong("bb");

            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入原sql文件夹名（绝对路径）：");
            if (scanner.hasNext()) {
                String next = scanner.next();
                System.out.println("输出一下；" + next);
            }

//        Assert.assertEquals(Long.valueOf(123), aLong);
//        Assert.assertEquals(Long.valueOf("12"), bb1);
    }


    @Test
    public void test1() {
        File file = new File("/Users/daxue0929/openSourceCodes/learnjava/src/main/resources/fish-tn-10541f5dbcfb4f85a58a4e2d2e0f4b31_dump_file");

        if (file.isDirectory()) {
            String parent = file.getParent();
            System.out.println(parent);
        }


    }
}
