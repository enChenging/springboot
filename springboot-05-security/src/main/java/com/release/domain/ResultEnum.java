package com.release.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yancheng
 * @since 2023/1/31
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    /**
     * 处理成功
     */
    SUCCESS(200, "处理成功"),
    /**
     * 处理失败
     */
    FAIL(500, "处理失败"),
    /**
     * 参数错误
     */
    PARAM_ERROR(400, "参数错误"),
    /**
     * 无权限
     */
    NO_POWER_ERROR(403, "无权限"),
    /**
     * token失效
     */
    TOKEN_EXPIRED(498, "token失效"),

    /**
     * refresh token失效
     */
    REFRESH_TOKEN_EXPIRED(499, "refresh token失效");

    private final int code;

    private final String msg;
}
