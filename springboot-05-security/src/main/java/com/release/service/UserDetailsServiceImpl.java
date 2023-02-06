package com.release.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.release.domain.LoginUser;
import com.release.domain.User;
import com.release.exception.BaseServiceException;
import com.release.mapper.MenuMapper;
import com.release.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yancheng
 * @since 2023/1/31
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper studentMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getStudentName, username);
        User user = studentMapper.selectOne(queryWrapper);
        if (user == null){
            throw new BaseServiceException("用户名或者密码错误");
        }
        //查询对应的权限信息
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user,list);
    }
}
