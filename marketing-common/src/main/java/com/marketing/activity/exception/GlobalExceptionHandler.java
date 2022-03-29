package com.marketing.activity.exception;

import com.marketing.activity.base.CommonResult;
import com.marketing.activity.constant.ErrorMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.Collections;

/**
 *
 * @author lee
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public CommonResult handleBaseException(BaseException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return CommonResult.failed(Collections.EMPTY_MAP, e.getMsgCode(), e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResult bindException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return CommonResult.failed(ErrorMsg.DATA_FORMAT_ERROR);
    }

    @ExceptionHandler(BindException.class)
    public CommonResult bindException(BindException e) {
        e.printStackTrace();
        log.error(e.getBindingResult().getFieldError().getDefaultMessage(), e);
        return CommonResult.failed(ErrorMsg.OPER_BASE_FAILD);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return CommonResult.failed(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    public CommonResult dateTimeParseException(DateTimeParseException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return CommonResult.failed(ErrorMsg.DATE_FORMAT_ERROR);
    }
}