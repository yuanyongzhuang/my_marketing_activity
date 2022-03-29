package com.marketing.activity.base;

import lombok.Getter;

/**
 * REST API 错误码.
 *
 * @author L
 */
public enum ApiErrorCode implements IErrorCode {

    /**
     * 失败
     */
    FAILED(-1, "失败"),

    /**
     * 成功
     */
    SUCCESS(0, "成功");

    @Getter
    private final long code;

    @Getter
    private final String message;

    ApiErrorCode(final long code, final String message) {
        this.code = code;
        this.message = message;
    }
}
