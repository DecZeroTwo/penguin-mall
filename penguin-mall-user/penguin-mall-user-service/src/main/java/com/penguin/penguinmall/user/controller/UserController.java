package com.penguin.penguinmall.user.controller;

import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.extra.mail.MailUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.domain.entity.po.ums.User;
import com.penguin.penguinmall.domain.entity.vo.UserRegisterVo;
import com.penguin.penguinmall.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Api(tags = "用户模块")
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService ius;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "login", notes = "用户登录")
    @GetMapping("/login")
    public HttpResp<Long> login(HttpServletRequest request, HttpServletResponse response, String username, String password, String captcha) {
        String vCodeId = request.getHeader("vCodeId");
        User user = ius.login(username, password, captcha, vCodeId);
        String salt = MD5.create().digestHex(username + ":" + password);

        String token = JWT.create().withClaim("username", username)
                .withClaim("userId", user.getUserId() + "")
                .withClaim("roleId", user.getUserRole() + "")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .sign(Algorithm.HMAC256(salt));
        log.debug("产生token:{}", token);
        stringRedisTemplate.opsForValue().set(token, salt);
        stringRedisTemplate.expire(token, 60, TimeUnit.MINUTES);
        String key = "user:" + user.getUserId();
        if (stringRedisTemplate.hasKey(key)) {
            String oldToken = stringRedisTemplate.opsForValue().get(key);
            stringRedisTemplate.delete(oldToken);
            stringRedisTemplate.opsForValue().set(key, token);
            stringRedisTemplate.expire(key, 120, TimeUnit.MINUTES);
        } else {
            stringRedisTemplate.opsForValue().set(key, token);
            stringRedisTemplate.expire(key, 120, TimeUnit.MINUTES);
        }

        response.addHeader("token", token);
        response.addHeader("Access-Control-Expose-Headers", "token");

        return HttpResp.success(user.getUserRole());
    }

    @ApiOperation(value = "logOut", notes = "用户退出")
    @GetMapping("/logOut")
    public HttpResp<String> logOut(Long userId, HttpServletRequest request) {
        String token = request.getHeader("token");
        stringRedisTemplate.delete(token);
        stringRedisTemplate.delete("user:" + userId);
        return HttpResp.success("退出成功");
    }


    @ApiOperation(value = "registry", notes = "用户注册")
    @PostMapping("/registry")
    public HttpResp registry(HttpServletRequest request, HttpServletResponse response, @RequestBody UserRegisterVo user) {
        String emailCaptchaId = request.getHeader("emailCaptchaId");
        ius.register(user, emailCaptchaId);
        return HttpResp.success("注册完成");
    }

    @ApiOperation(value = "captcha", notes = "获取验证码")
    @GetMapping("/captcha")
    public HttpResp<String> code(HttpServletResponse response) {
        LineCaptcha lineCaptcha = new LineCaptcha(200, 100);
        String code = lineCaptcha.getCode();
        String imageBase64Data = lineCaptcha.getImageBase64Data();
        Snowflake snowflake = new Snowflake();
        String browserId = snowflake.nextIdStr();
        response.addHeader("vCodeId", browserId);
        response.addHeader("Access-Control-Expose-Headers", "vCodeId");
        stringRedisTemplate.opsForValue().set(browserId, code);
        stringRedisTemplate.expire(browserId, 60, TimeUnit.SECONDS);
        return HttpResp.success(imageBase64Data);
    }

    @ApiOperation(value = "emailCaptcha", notes = "邮箱验证码")
    @GetMapping("/emailCaptcha")
    public HttpResp<String> emailCaptcha(HttpServletResponse response, String email) {
        LineCaptcha lineCaptcha = new LineCaptcha(200, 100);
        String code = lineCaptcha.getCode();
        Snowflake snowflake = new Snowflake();
        String emailCaptchaId = snowflake.nextIdStr();
        response.addHeader("emailCaptchaId", emailCaptchaId);
        response.addHeader("Access-Control-Expose-Headers", "emailCaptchaId");
        MailUtil.send(email, "注册验证码", code, false);
        stringRedisTemplate.opsForValue().set(emailCaptchaId, code, 60, TimeUnit.SECONDS);
        System.out.println(HttpResp.success("验证码发送成功"));
        return HttpResp.success("验证码发送成功");
    }
}
