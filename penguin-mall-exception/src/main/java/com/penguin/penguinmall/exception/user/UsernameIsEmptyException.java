package com.penguin.penguinmall.exception.user;

/**
 * 用户名为空异常
 */
public class UsernameIsEmptyException extends UserException {

    public UsernameIsEmptyException(String message) {
        super(message);
    }
}
