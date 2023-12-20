package com.penguin.penguinmall.exception.user;

/**
 * 错误凭证（密码)
 */
public class BadCredentialsException extends UserException {

    public BadCredentialsException(String message) {
        super(message);
    }
}
