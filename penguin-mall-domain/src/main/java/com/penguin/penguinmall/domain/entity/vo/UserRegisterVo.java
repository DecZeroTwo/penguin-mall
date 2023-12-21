package com.penguin.penguinmall.domain.entity.vo;

import com.penguin.penguinmall.domain.entity.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterVo extends User {
    private String emailCaptcha;
}
