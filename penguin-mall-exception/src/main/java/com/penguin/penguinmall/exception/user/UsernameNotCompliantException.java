package com.penguin.penguinmall.exception.user;

/**
 * 用户名不匹配规则异常
 */
public class UsernameNotCompliantException extends UserException {

    public UsernameNotCompliantException(String message) {
        super(message);
    }
}
