package com.daxue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;

@Controller
@ResponseBody
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String test() {
        System.out.println("test进来了");
        return "中文"; // 中文乱码的原因
    }

}
