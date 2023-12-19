package com.penguin.penguinmall.common.result;

import lombok.Getter;

@Getter
public enum RespCode {
    SUCCESS(2000, "success"),
    FAILED(5000, "success"),
    INFO(8000, "INFO");
    int code;
    String msg;

    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
