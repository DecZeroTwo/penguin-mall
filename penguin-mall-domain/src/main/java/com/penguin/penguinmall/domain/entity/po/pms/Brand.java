package com.penguin.penguinmall.domain.entity.po.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 品牌
 * @TableName pms_brand
 */
@TableName(value ="pms_brand")
@Data
public class Brand implements Serializable {
    /**
     * 品牌id
     */
    @TableId(value = "brand_id", type = IdType.AUTO)
    private Long brandId;

    /**
     * 品牌名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 品牌logo地址
     */
    @TableField(value = "logo")
    private String logo;

    /**
     * 介绍
     */
    @TableField(value = "descript")
    private String descript;

    /**
     * 显示状态[0-不显示；1-显示]
     */
    @TableField(value = "show_status")
    private Integer showStatus;

    /**
     * 检索首字母
     */
    @TableField(value = "first_letter")
    private String firstLetter;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}