package com.penguin.penguinmall.exception.user;

/**
 * 用户重名异常
 */
public class DupNameException extends UserException {

    public DupNameException(String message) {
        super(message);
    }
}
