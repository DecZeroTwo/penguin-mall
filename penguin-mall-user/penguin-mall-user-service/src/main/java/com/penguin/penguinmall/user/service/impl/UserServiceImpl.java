package com.penguin.penguinmall.user.service.impl;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.penguin.penguinmall.domain.entity.po.User;
import com.penguin.penguinmall.domain.entity.vo.UserRegisterVo;
import com.penguin.penguinmall.exception.user.*;
import com.penguin.penguinmall.mq.config.RabbitMQConfig;
import com.penguin.penguinmall.user.dao.UserDao;
import com.penguin.penguinmall.user.service.IUserService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void warnUpUsernames() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ne(User::getUserState, -1);
        userDao.selectList(lambdaQueryWrapper).forEach(u -> {
                    stringRedisTemplate.opsForValue()
                            .getOperations()
                            .execute(new DefaultRedisScript<Long>(
                                            "return redis.call('bf.add',KEYS[1],ARGV[1])"
                                            , Long.class),
                                    new ArrayList<String>() {{
                                        add("whiteUsernames");
                                    }}, u.getUsername()
                            );
                }
        );
    }

    @Override
    public User login(String username, String password, String captcha, String vCodeId) {
        if (stringRedisTemplate.hasKey(vCodeId)) {
            String s = stringRedisTemplate.opsForValue().get(vCodeId);
            if (!s.equalsIgnoreCase(captcha)) {
                throw new VerifyException("验证码错误");
            }
        } else {
            throw new VerifyException("验证码cookie异常");
        }
        if (!StringUtils.hasText(username)) {
            throw new UsernameIsEmptyException("用户名为空异常");
        }
        username = username.trim();
        if (checkFromWhite(username)) {
            throw new UsernameNotFoundException("用户名不存在异常");
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUsername, username)
                .eq(User::getPassword, MD5.create().digestHex(password))
                .ne(User::getUserState, -1);
        User user = userDao.selectOne(queryWrapper);

        if (Objects.isNull(user)) {
            throw new BadCredentialsException("用户名|密码错误");
        }

        return user;
    }

    /**
     * 判断用户名是否存在于布隆过滤器
     *
     * @param username 用户名
     * @return 0 不存在
     */
    private boolean checkFromWhite(String username) {

        Long isExist = stringRedisTemplate.opsForValue()
                .getOperations()
                .execute(new DefaultRedisScript<Long>("return redis.call('bf.exists',KEYS[1],ARGV[1])", Long.class),
                        new ArrayList<String>() {{
                            add("whiteUsernames");
                        }}, username);

        return isExist.intValue() == 0;
    }

    @Override
    public void register(UserRegisterVo user,String emailCaptchaId) {
        String browserEmailCaptcha = stringRedisTemplate.opsForValue().get(emailCaptchaId);
        if (!browserEmailCaptcha.equals(user.getEmailCaptcha())) {
            throw new EmailCaptchaException("邮箱验证码错误");
        }
        String username = user.getUsername();
        if (!StringUtils.hasText(username)) {
            throw new UsernameIsEmptyException("用户名为空");
        }
        if (!username.matches("[a-zA-Z0-9]{6,10}")) {
            throw new UsernameNotCompliantException("用户名不匹配规则");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User dupName = userDao.selectOne(queryWrapper);
        if (Objects.nonNull(dupName)) {
            throw new DupNameException("用户重名");
        }
        user.setPassword(MD5.create().digestHex(user.getPassword()));
        rabbitTemplate.convertAndSend(RabbitMQConfig.PENGUINMALL_REGISTRY_QUEUE, user);
    }

    @RabbitListener(queues = RabbitMQConfig.PENGUINMALL_REGISTRY_QUEUE)
    @Override
    public void registerUser(User user, Message message, Channel channel) {
        Date createTime = new Date();
        user.setCreateTime(createTime);
        user.setUpdateTime(createTime);
        user.setUserState(0);
        userDao.insert(user);
        this.warnUpUsernames();
        MailUtil.send(user.getEmail(), "企鹅商城", "注册成功", false);
    }
}
