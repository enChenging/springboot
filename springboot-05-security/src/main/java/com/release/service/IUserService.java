package com.release.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.release.domain.User;
import com.release.domain.StudentVO;

/**
 * 用户表 服务类
 * @author yancheng
 * @since 2023/1/31
 */

public interface IUserService extends IService<User> {


    /**
     * 学生信息
     *
     * @return
     */
    StudentVO getStudentInfo();

}
