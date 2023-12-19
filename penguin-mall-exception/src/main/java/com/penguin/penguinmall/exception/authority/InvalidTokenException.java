package com.penguin.penguinmall.exception.authority;

/**
 * 无效的token
 */
public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException(String message) {
        super(message);
    }
}
