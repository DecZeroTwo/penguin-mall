package com.penguin.penguinmall.exception.authority;

/**
 * 没有权限
 */
public class PermissionDeniedException extends AuthenticationException{

    public PermissionDeniedException(String message) {
        super(message);
    }
}
