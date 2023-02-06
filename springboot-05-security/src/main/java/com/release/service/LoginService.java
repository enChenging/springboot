package com.release.service;


import com.release.domain.Result;
import com.release.domain.User;
import com.release.domain.TokenVO;

/**
 * @author yancheng
 * @since 2023/1/31
 */

public interface LoginService {


    /**
     * 登录
     * @param student
     * @return
     */
    Result<TokenVO> login(User student);

    /**
     * 退出登录
     * @return
     */
    Result<String> logout();
}
