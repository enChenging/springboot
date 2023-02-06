package com.release.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 菜单 实体类
 * @author yancheng
 * @since 2023/1/31
 */

@Data
@Accessors(chain = true)
@TableName("auth_menu")
public class Menu {

    @TableId
    private Long id;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String studentName;


    /**
     * 功能描述
     */
    @TableField("description")
    private String description;


    /**
     * 是否公开菜单
     */
    @TableField("is_public")
    private Integer isPublic;


    /**
     * 对应路由path
     */
    @TableField("path")
    private String path;


    /**
     * 对应路由组件component
     */
    @TableField("component")
    private String component;


    /**
     * 权限关键字
     */
    @TableField("perms")
    private String perms;


    /**
     * 状态 0: 启用  1:禁用
     */
    @TableField("status")
    private Integer status;


    /**
     * 排序
     */
    @TableField("sort_value")
    private Integer sortValue;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 菜单分组
     */
    @TableField("group_value")
    private String groupValue;

    /**
     * 父级菜单id
     */
    @TableField("parent_id")
    private Long parentId;
}