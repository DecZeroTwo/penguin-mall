package com.penguin.penguinmall.user.feign;

import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.domain.entity.vo.UserRegisterVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FeignClient(value = "penguin-mall-user")
public interface IUserFeign {
    @GetMapping("/api/user/login")
    HttpResp<String> login(HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("captcha") String captcha, @RequestParam("vCodeId") String vCodeId);

    @GetMapping("/api/user/logOut")
    HttpResp<String> logOut(@RequestParam("userId") Long userId, HttpServletRequest request, HttpServletResponse response);

    @PostMapping("/api/user/register")
    HttpResp<String> register(@RequestBody UserRegisterVo user);

    @GetMapping("/api/user/captcha")
    HttpResp<String> code(HttpServletResponse response);

    @GetMapping("/api/user/emailCaptcha")
    HttpResp<String> emailCaptcha(HttpServletResponse response, @RequestParam("email") String email);
}
