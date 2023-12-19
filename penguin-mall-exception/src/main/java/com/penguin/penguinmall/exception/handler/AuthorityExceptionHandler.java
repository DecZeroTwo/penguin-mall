package com.penguin.penguinmall.exception.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.exception.authority.InvalidTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AuthorityExceptionHandler {
    @ExceptionHandler(InvalidTokenException.class)
    public HttpResp invalidTokenException(InvalidTokenException e) {
        return HttpResp.failed(e.getMessage());
    }

    @ExceptionHandler(JWTVerificationException.class)
    public HttpResp jwtVerificationException(JWTVerificationException e) {
        return HttpResp.failed(e.getMessage());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public HttpResp tokenExpiredException(TokenExpiredException e) {
        return HttpResp.failed(e.getMessage());
    }
}
