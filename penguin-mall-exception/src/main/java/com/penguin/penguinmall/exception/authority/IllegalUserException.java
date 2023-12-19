package com.penguin.penguinmall.exception.authority;

public class IllegalUserException extends RuntimeException {
    public IllegalUserException(String message) {
        super(message);
    }
}
