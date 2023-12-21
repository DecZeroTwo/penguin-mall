package com.penguin.penguinmall.domain.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName ums_user_tab
 */
@TableName(value ="ums_user_tab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 
     */
    @TableField(value = "user_username")
    private String username;

    /**
     * 
     */
    @TableField(value = "user_password")
    private String password;

    /**
     * 
     */
    @TableField(value = "email")
    private String email;

    /**
     * 
     */
    @TableField(value = "user_update_time")
    private Date updateTime;

    /**
     * 
     */
    @TableField(value = "user_create_time")
    private Date createTime;

    /**
     * 1系统管理员 2用户
     */
    @TableField(value = "user_role")
    private Long userRole;

    /**
     * 
     */
    @TableField(value = "user_state")
    private Integer userState;
}