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
@TableName("auth_user")
public class User {

    @TableId
    private Long id;

    /**
     * 学生姓名
     */
    @TableField("student_name")
    private String studentName;


    /**
     * 学校id
     */
    @TableField("school_id")
    private Long schoolId;


    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;


    /**
     * 密码
     */
    @TableField("password")
    private String password;


    /**
     * 学情分析考号(带JN)
     */
    @TableField("analysis_no")
    private String analysisNo;


    /**
     * 学生考号(不带JN)
     */
    @TableField("student_exam_no")
    private String studentExamNo;


    /**
     * 年级
     */
    @TableField("year_name")
    private Integer yearName;


    /**
     * 上次登录时间
     */
    @TableField("login_time")
    private Date loginTime;

    /**
     * 是否删除0否1是
     */
    @TableField("is_deleted")
    private Integer isDeleted;

    /**
     * 班级名
     */
    @TableField(exist = false)
    private String classroomName;

    /**
     * 赋分总成绩
     */
    @TableField(exist = false)
    private BigDecimal weightCityScore;
}