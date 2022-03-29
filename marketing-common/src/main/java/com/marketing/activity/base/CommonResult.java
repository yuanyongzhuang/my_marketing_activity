package com.marketing.activity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResult<T> implements Serializable {

    private long code;

    private String msg;

    private T data;

    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(ApiErrorCode.SUCCESS.getCode(), ApiErrorCode.SUCCESS.getMessage(), null);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ApiErrorCode.SUCCESS.getCode(), ApiErrorCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ApiErrorCode.FAILED.getCode(), message, null);
    }

    public static <T> CommonResult<T> failed(T t, ApiErrorCode apiErrorCode, String message) {
        CommonResult<T> tCommonResult = new CommonResult(ApiErrorCode.FAILED.getCode(), message,
                t == null ? Collections.EMPTY_MAP : t);
        if(apiErrorCode != null){
            tCommonResult.setCode(apiErrorCode.getCode());
            if(StringUtils.isBlank(message)){
                tCommonResult.setMsg(apiErrorCode.getMessage());
            }
        }
        return tCommonResult;
    }



    public boolean ok() {
        return ApiErrorCode.SUCCESS.getCode() == code;
    }

}
