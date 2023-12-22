package com.penguin.penguinmall.domain.entity.po.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品属性
 * @TableName pms_attr
 */
@TableName(value ="pms_attr")
@Data
public class Attr implements Serializable {
    /**
     * 属性id
     */
    @TableId(value = "attr_id", type = IdType.AUTO)
    private Long attrId;

    /**
     * 属性名
     */
    @TableField(value = "attr_name")
    private String attrName;

    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    @TableField(value = "search_type")
    private Integer searchType;

    /**
     * 值类型[0-为单个值，1-可以选择多个值]
     */
    @TableField(value = "value_type")
    private Integer valueType;

    /**
     * 属性图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 可选值列表[用逗号分隔]
     */
    @TableField(value = "value_select")
    private String valueSelect;

    /**
     * 属性类型[0-销售属性，1-基本属性
     */
    @TableField(value = "attr_type")
    private Integer attrType;

    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @TableField(value = "enable")
    private Long enable;

    /**
     * 所属分类
     */
    @TableField(value = "catelog_id")
    private Long catelogId;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    @TableField(value = "show_desc")
    private Integer showDesc;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}