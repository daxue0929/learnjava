package com.daxue.boot3.boot301demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daxue0929
 * @date 2023/6/18
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "hello world";
    }
}
