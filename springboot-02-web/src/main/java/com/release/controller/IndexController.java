package com.release.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 在templates目录下的所有页面，只能通过controller来跳转
 * 需要模板引擎的支持
 * @author yancheng
 * @since 2022/7/8
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index2";
    }
}
