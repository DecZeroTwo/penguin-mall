package com.penguin.penguinmall.exception.user;

/**
 * 错误凭证（密码)
 */
public class EmailCaptchaException extends UserException {

    public EmailCaptchaException(String message) {
        super(message);
    }
}
