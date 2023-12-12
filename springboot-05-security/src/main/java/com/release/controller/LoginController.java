package com.release.controller;

import com.release.domain.Result;
import com.release.domain.User;
import com.release.domain.TokenVO;
import com.release.service.LoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yancheng
 * @since 2023/2/1
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     * 密码传：123
     * 数据库密码对应的是加密后的 $2a$10$VGWEhKE7EsUXpzz84Q/WZOzw5iEd9Y.vNh/IOYzZx17fSCK2p9pnG
     * @param student
     * @return
     */
    @RequestMapping("/user/login")
    public Result<TokenVO> login(@RequestBody User student){
        return loginService.login(student);
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/user/logout")
    public Result<String> logout(){
        return loginService.logout();
    }
}
