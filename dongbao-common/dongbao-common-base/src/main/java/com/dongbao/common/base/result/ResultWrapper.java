package com.dongbao.common.base.result;

import com.dongbao.common.base.enums.StateCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther zhangjq
 * @Date 2022/6/21
 * @Description com.dongbao.common.base.result
 * @Version 1.0
 */
@Data
@Builder
public class ResultWrapper<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public static <T> ResultWrapper.ResultWrapperBuilder<T> getSuccessBuilder() {
        return ResultWrapper.<T>builder().code(StateCodeEnum.SUCCESS.getCode())
                .msg(StateCodeEnum.SUCCESS.getMsg());
    }

    public static <T> ResultWrapper.ResultWrapperBuilder<T> getFailBuilder() {
        return ResultWrapper.<T>builder().code(StateCodeEnum.FAIL.getCode())
                .msg(StateCodeEnum.FAIL.getMsg());
    }
}
