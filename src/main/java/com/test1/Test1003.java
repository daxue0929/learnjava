package com.test1;

import org.junit.Test;

import java.io.ByteArrayInputStream;

public class Test1003 {

    @Test
    public void test01() {

        System.out.println("hello world");

    }


    @Test
    public void test02() {
        /**
         * byte = 8bit    0~255
         * short = 16bit
         * int  = 32bit
         * long = 64bit
         */

        // InputStream  输入字节流

        Integer number = 1;
        byte[] b = {12};

        byte[] bytes = "1".getBytes();


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        int read = byteArrayInputStream.read();

    }


}
