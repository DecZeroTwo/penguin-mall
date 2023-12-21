package com.penguin.penguinmall.exception.handler;

import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.exception.user.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserException.class)
    public HttpResp invalidTokenException(UserException e) {
        return HttpResp.failed(e.getMessage());
    }
}
