package com.release.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.release.exception.BaseServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author yancheng
 * @since 2023/1/31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Result<T> {
    private static final String DELIMITER = "&&&&";

    /**
     * 响应代码
     */
    private int code;
    /**
     * 返回的提示信息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private T data;


    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 正确返回
     *
     * @param <T> 返回的数据类型
     * @return Result
     */
    public static <T> Result<T> ok() {
        return restResult(ResultEnum.SUCCESS.getCode(), null, ResultEnum.SUCCESS.getMsg());
    }

    /**
     * 带数据的正确返回
     *
     * @param data 返回的数据
     * @param <T>  返回的数据类型
     * @return Result
     */
    public static <T> Result<T> ok(T data) {
        return restResult(ResultEnum.SUCCESS.getCode(), data, ResultEnum.SUCCESS.getMsg());
    }

    /**
     * 带数据以及提示信息的正确返回
     *
     * @param data 返回的数据
     * @param msg  返回的提示信息
     * @param <T>  返回的数据类型
     * @return Result
     */
    public static <T> Result<T> ok(T data, String msg) {
        return restResult(ResultEnum.SUCCESS.getCode(), data, msg);
    }

    /**
     * 错误返回
     *
     * @param <T> 返回的数据类型
     * @return Result
     */
    public static <T> Result<T> failed() {
        return restResult(ResultEnum.FAIL.getCode(), null, ResultEnum.FAIL.getMsg());
    }

    /**
     * 带提示信息的错误返回
     *
     * @param msg 返回的提示信息
     * @param <T> 返回的数据类型
     * @return Result
     */
    public static <T> Result<T> failed(String msg) {
        if (msg.contains(DELIMITER)) {
            int index = msg.indexOf(DELIMITER);
            String message = msg.substring(0, index);
            int code = Integer.parseInt(msg.substring(index + DELIMITER.length()));
            return restResult(code, null, message);
        }
        return restResult(ResultEnum.FAIL.getCode(), null, msg);
    }

    /**
     * 带数据以及提示信息的错误返回
     *
     * @param data 返回的数据
     * @param msg  返回的提示信息
     * @param <T>  返回的数据类型
     * @return Result
     */
    public static <T> Result<T> failed(T data, String msg) {
        if (msg.contains(DELIMITER)) {
            int index = msg.indexOf(DELIMITER);
            String message = msg.substring(0, index);
            int code = Integer.parseInt(msg.substring(index + DELIMITER.length()));
            return restResult(code, data, message);
        }
        return restResult(ResultEnum.FAIL.getCode(), data, msg);
    }

    /**
     * 自定义状态码
     *
     * @param code 状态码
     * @param data 返回的数据
     * @param msg  返回的提示信息
     * @param <T>  返回的数据类型
     * @return Result
     */
    public static <T> Result<T> other(int code, T data, String msg) {
        return restResult(code, data, msg);
    }

    private static <T> Result<T> restResult(int code, T data, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    /**
     * 转换对象
     *
     * @param result 参数
     * @param <U>    返回类型
     * @return 返回值
     */
    @JsonIgnore
    public static <U> Result<U> empty(Result<?> result) {
        @SuppressWarnings("unchecked") Result<U> t = (Result<U>) result;
        return t;
    }

    /**
     * 返回调用成功的数据
     * 只有当code为200的时候才能获取到数据
     *
     * @return 数据
     */
    @JsonIgnore
    public T successData() {
        if (this.code == ResultEnum.SUCCESS.getCode()) {
            return this.data;
        }
        throw new BaseServiceException(this.msg, this.code);
    }


    /**
     * 如果成功转 Optional
     *
     * @param function 异常方法
     * @param <X>      异常
     * @return
     */
    @JsonIgnore
    public <X extends BaseServiceException> Optional<Result<T>> then(Function<Result<T>, ? extends X> function) {
        Objects.requireNonNull(function);
        if (!isSuccess()) {
            throw function.apply(this);
        } else {
            return Optional.of(this);
        }


    }


    /**
     * Result转opt
     *
     * @return
     */
    public Optional<Result<T>> opt() {
        return Optional.of(this);
    }


    /**
     * Result转opt
     *
     * @return
     */
    public Optional<Result<T>> opt(Function<Result<T>, Result<T>> success, Function<Result<T>, Result<T>> failure) {

        Objects.requireNonNull(failure);
        Objects.requireNonNull(success);
        if (isSuccess()) {
            return Optional.of(success.apply(this));
        } else {
            return Optional.of(failure.apply(this));
        }
    }


//  /**
//   * 获取数据  200 则获取数据, 500抛出异常
//   *
//   * @param function 执行方法
//   * @param <X>      异常
//   * @return 数据
//   * @throws X 抛出的业务异常
//   */
//  @JsonIgnore
//  public <X extends BaseServiceException> T orThrow(Function<Result<T>, ? extends X> function) throws X {
//    List.
//
//    if (isSuccess()) {
//      return this.data;
//    } else {
//      throw function.apply(this);
//    }
//  }


    /**
     * 是否成功
     *
     * @return 状态
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.code == ResultEnum.SUCCESS.getCode();
    }


}
