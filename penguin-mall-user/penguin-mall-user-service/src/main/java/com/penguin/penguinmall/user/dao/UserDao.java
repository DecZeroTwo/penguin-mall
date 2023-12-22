package com.penguin.penguinmall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.penguin.penguinmall.domain.entity.po.ums.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
