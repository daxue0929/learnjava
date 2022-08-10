package com.test2;

import java.util.regex.Pattern;

/**
 * @author daxue0929
 * @date 2022/6/27
 */

public class TestAA {

    private static final Pattern HTTPS_PATTERN = Pattern.compile("https?://");

    public static void main(String[] args) {

        String originalURL = "https://daxueaaa123.com";
        String forwardedScheme = "https";

        String s = HTTPS_PATTERN.matcher(originalURL.toString()).replaceFirst(forwardedScheme + "://");
        System.out.println(s);


    }

}
