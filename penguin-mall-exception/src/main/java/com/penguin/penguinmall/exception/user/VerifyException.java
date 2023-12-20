package com.penguin.penguinmall.exception.user;

/**
 * 验证码错误
 */
public class VerifyException extends UserException {

    public VerifyException(String message) {
        super(message);
    }
}
