package com.release.controller;

import com.release.domain.Result;
import com.release.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yancheng
 * @since 2022/7/8
 */
@RestController
public class HelloWorldController {

    @Resource
    private IUserService studentService;

    @RequestMapping("/hello")
//    @PreAuthorize(value = "hasAuthority('manager:index')")
    //自定义权限校验方法
    @PreAuthorize("@re.hasAuthorized('manager:index')")
    public Result<String> hello(){
//        StudentVO studentInfo = studentService.getStudentInfo();
        return Result.ok("hello");
    }

    @RequestMapping("/menu")
    public Result<String> menu(){
        return Result.ok("菜单");
    }

}
