package com.release.service;

import com.alibaba.fastjson.JSONObject;
import com.release.domain.LoginUser;
import com.release.domain.Result;
import com.release.domain.User;
import com.release.domain.TokenVO;
import com.release.utils.ConstantUtils;
import com.release.utils.JwtTokenUtil;
import com.release.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;


/**
 * @author yancheng
 * @since 2023/1/31
 */
@Service
@Slf4j
public class LoginServiceImpl  implements LoginService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public Result<TokenVO> login(User user) {
        //AuthenticationManager 调用authenticate进行用户认证，就会调用loadUserByUsername方法查询用户
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        //如果认证通过，使用studentId生成一个jwt  jwt存入到Result返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long studentId = loginUser.getUser().getId();
        String token = JwtTokenUtil.getToken(studentId);
        //把完整的用户信息存入redis studentId作为key
        boolean set = redisUtil.set(ConstantUtils.LOGIN_REDIS_KEY + studentId, JSONObject.toJSON(loginUser));
        return Result.ok(new TokenVO(token));
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public Result<String> logout() {
        //获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long studentId = loginUser.getUser().getId();
        //删除redis中的值
        redisUtil.del(ConstantUtils.LOGIN_REDIS_KEY + studentId);
        return Result.ok("注销成功");
    }
}
