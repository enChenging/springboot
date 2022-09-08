package com.release.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yancheng
 * @since 2022/7/8
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello world springboot";
    }
}
