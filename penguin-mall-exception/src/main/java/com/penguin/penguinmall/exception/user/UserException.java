package com.penguin.penguinmall.exception.user;

/**
 * 用户注册的异常基类
 */
public class UserException extends RuntimeException{

    public UserException(String message) {
        super(message);
    }
}
