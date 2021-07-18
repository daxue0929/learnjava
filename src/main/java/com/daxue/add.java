package com.daxue;

/**
 * @author daxue0929
 * @date 2021/06/22
 **/
public class add {
    public static void main(String[] args){
        String buff = "Y2hlbmppbmdjb25n";

        String result = "";
        for (int i = 0; i <= 1688; i++) {
            result += buff + String.valueOf(i) + "+";
        }

        System.out.println(result);


    }
}
