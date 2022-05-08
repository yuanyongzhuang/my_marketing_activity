package com.marketing.activity.exception;

import com.marketing.activity.base.IErrorCode;

/**
 * 领取优惠券异常类
 * @author yyz
 */
public class ReceiveVoucherException extends RuntimeException {

    private IErrorCode errorCode;

    public ReceiveVoucherException(IErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ReceiveVoucherException(String message){
        super(message);
    }

    public ReceiveVoucherException(Throwable cause){
        super(cause);
    }

    public ReceiveVoucherException(String message, Throwable cause){
        super(message,cause);
    }

    public IErrorCode getErrorCode(){
        return errorCode;
    }
}
