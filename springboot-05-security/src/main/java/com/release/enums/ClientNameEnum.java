package com.release.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客户端枚举
 *
 * @author yancheng
 * @since 2023/1/31
 */
@Getter
@AllArgsConstructor
public enum ClientNameEnum {

    WEB("web", "web端"),

    WECHAT("wechat", "小程序端");

    private String name;

    private String msg;
}
