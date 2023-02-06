package com.release.exception;

/**
 * @author yancheng
 * @since 2023/1/31
 */
public class BaseServiceException extends RuntimeException {

    private static final long serialVersionUID = -5754126462432027437L;
    private static final String DELIMITER = "&&&&";

    /**
     *
     * @param message  错误信息
     */
    public BaseServiceException(String message) {
        super(message);
    }

    /**
     *
     * @param message 错误信息
     * @param code 自定义状态码
     */
    public BaseServiceException(String message,int code) {
        super(message+DELIMITER+code);
    }
}
