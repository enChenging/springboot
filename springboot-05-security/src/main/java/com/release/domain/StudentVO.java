package com.release.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户表 显示层对象
 * @author yancheng
 * @since 2023/1/31
 */

@Data
@Accessors(chain = true)
public class StudentVO {

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学情分析考号(带JN)
     */
    private String analysisNo;

    /**
     * 学生考号(不带JN)
     */
    private String studentExamNo;

    /**
     * 年级名称
     */
    private Integer yearName;

}