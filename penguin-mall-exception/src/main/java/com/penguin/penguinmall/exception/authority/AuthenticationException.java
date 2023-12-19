package com.penguin.penguinmall.exception.authority;

/**
 * 鉴权认证模块的异常基类
 */
public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String message) {
        super(message);
    }
}
