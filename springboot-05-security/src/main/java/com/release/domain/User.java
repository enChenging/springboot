package com.release.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表 实体类
 * @author yancheng
 * @since 2023/1/31
 */

@Data
@Accessors(chain = true)
@TableName("user")
public class User {

    @TableId
    private Long id;

    /**
     * 用户姓名
     */
    @TableField("user_name")
    private String userName;


    /**
     * 密码
     */
    @TableField("password")
    private String password;


}