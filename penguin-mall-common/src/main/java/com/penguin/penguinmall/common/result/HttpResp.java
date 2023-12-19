package com.penguin.penguinmall.common.result;

import lombok.Getter;

import java.util.Date;

/**
 * 设计统一的返回对象
 *
 * @param <T>
 */
@Getter
public class HttpResp<T> {
    private int code;
    private String msg;
    private T result;
    private Date time;
    private String ip;
    private int port;

    public static <T> HttpResp<T> success(T result){
        HttpResp<T> httpResp = new HttpResp<>();
        httpResp.code=RespCode.SUCCESS.getCode();
        httpResp.msg = RespCode.SUCCESS.getMsg();
        httpResp.result = result;
        httpResp.time = new Date();
        return httpResp;
    }

    public static <T> HttpResp<T> failed(T result,Object ... values){
        HttpResp<T> httpResp = new HttpResp<>();
        httpResp.code=RespCode.FAILED.getCode();
        httpResp.msg = RespCode.FAILED.getMsg();
        httpResp.result = result;
        httpResp.time = new Date();
        httpResp.ip =(String)values[0];
        httpResp.port =(int) values[1];
        return httpResp;
    }
    public static <T> HttpResp<T> failed(String msg){
        HttpResp<T> httpResp = new HttpResp<>();
        httpResp.code=RespCode.FAILED.getCode();
        httpResp.msg = msg;

        return httpResp;
    }
}
