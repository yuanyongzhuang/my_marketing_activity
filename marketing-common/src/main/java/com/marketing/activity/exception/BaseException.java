package com.marketing.activity.exception;


import com.marketing.activity.base.ApiErrorCode;

/**
 *
 * @author lee
 */
public class BaseException extends RuntimeException {

    private ApiErrorCode msgCode;

    public BaseException(ApiErrorCode msgCode) {
        super(msgCode.getMessage());
        this.msgCode = msgCode;
    }

    /**
     * 根据指定的描述信息构建一个异常
     *
     * @param message
     */
    public BaseException(String message) {
        super(message);
    }

    public BaseException(ApiErrorCode msgCode, String message) {
        super(message);
        this.msgCode = msgCode;
    }

    public ApiErrorCode getMsgCode() {
        return msgCode;
    }

    @Override
    public String getMessage() {
        String msg = super.getMessage();
        return msg;
    }
}