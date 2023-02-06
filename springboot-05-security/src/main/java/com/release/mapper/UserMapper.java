package com.release.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.release.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表 Mapper 接口
 * @author yancheng
 * @since 2023/1/31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
