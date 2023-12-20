package com.penguin.penguinmall.exception.user;

/**
 * 用户名不存在异常
 */
public class UsernameNotFoundException extends UserException {

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
