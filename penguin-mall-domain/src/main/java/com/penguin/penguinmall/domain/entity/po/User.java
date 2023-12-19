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
    private String userUsername;

    /**
     * 
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     * 
     */
    @TableField(value = "email")
    private String email;

    /**
     * 
     */
    @TableField(value = "user_update_time")
    private Date userUpdateTime;

    /**
     * 
     */
    @TableField(value = "user_create_time")
    private Date userCreateTime;

    /**
     * 1系统管理员 2用户管理员 3商城管理员 4老人 5子女
     */
    @TableField(value = "user_role")
    private Integer userRole;

    /**
     * 
     */
    @TableField(value = "user_state")
    private Integer userState;
}