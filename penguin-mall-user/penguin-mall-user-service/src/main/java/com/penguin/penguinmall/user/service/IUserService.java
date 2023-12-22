package com.penguin.penguinmall.user.service;

import com.penguin.penguinmall.domain.entity.po.ums.User;
import com.penguin.penguinmall.domain.entity.vo.UserRegisterVo;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

public interface IUserService {

    /**
     * 从数据库读取用户名信息存入布隆过滤器中
     */
    void warnUpUsernames();

    User login(String username, String password, String captcha, String vCodeId);


    void register(UserRegisterVo user,String browserEmailCaptcha);

    void registerUser(User user, Message message, Channel channel) throws IOException;
}
