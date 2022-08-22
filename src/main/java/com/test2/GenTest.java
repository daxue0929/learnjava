package com.test2;

import java.util.UUID;

/**
 * @author daxue0929
 * @date 2022/06/09
 **/
public class GenTest {

    public static void main(String[] args){
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }


}
