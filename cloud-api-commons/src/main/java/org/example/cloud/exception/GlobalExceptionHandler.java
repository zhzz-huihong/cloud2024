package org.example.cloud.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.cloud.response.Result;
import org.example.cloud.response.ReturnCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(Exception e){
        e.printStackTrace();
        log.error("全局异常信息：" + e.getMessage());
        return Result.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }
}
