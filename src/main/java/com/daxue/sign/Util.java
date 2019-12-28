package com.daxue.sign;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import static java.security.MessageDigest.*;

public class Util {
    public static void main(String[] args) {
        MessageDigest md5;
        try {
            md5 = getInstance("MD5");

            Calendar instance = Calendar.getInstance();
            System.out.println(instance.getTimeInMillis());



        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
