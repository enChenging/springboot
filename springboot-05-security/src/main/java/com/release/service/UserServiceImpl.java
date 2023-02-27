package com.release.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.release.domain.User;
import com.release.domain.StudentVO;
import com.release.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 用户表 服务实现类
 * @author yancheng
 * @since 2023/1/31
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    /**
     * 学生信息
     *
     * @return
     */
    @Override
    public StudentVO getStudentInfo() {
        User stu = this.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserName, "zs"));
        StudentVO studentVO = new StudentVO();
        BeanUtil.copyProperties(stu,studentVO);
        return studentVO;
    }

}
