package com.release.filter;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.release.domain.LoginUser;
import com.release.exception.BaseServiceException;
import com.release.utils.ConstantUtils;
import com.release.utils.JwtTokenUtil;
import com.release.utils.RedisUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yancheng
 * @since 2023/2/1
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String header = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        if (ObjectUtil.isEmpty(header)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.split(" ")[1];
        //解析token
        Long studentId;
        try {
            studentId = JwtTokenUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("非法的token");
        }
        //从redis中获取用户信息
        Object o = redisUtil.get(ConstantUtils.LOGIN_REDIS_KEY + studentId);
        LoginUser loginUser = JSONObject.parseObject(JSONObject.toJSONString(o),LoginUser.class);
        if (ObjectUtil.isEmpty(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        // 获取权限信息封装到 Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);

    }
}
