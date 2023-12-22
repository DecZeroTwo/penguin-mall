package com.penguin.penguinmall.domain.entity.po.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 属性分组
 * @TableName pms_attr_group
 */
@TableName(value ="pms_attr_group")
@Data
public class AttrGroup implements Serializable {
    /**
     * 分组id
     */
    @TableId(value = "attr_group_id", type = IdType.AUTO)
    private Long attrGroupId;

    /**
     * 组名
     */
    @TableField(value = "attr_group_name")
    private String attrGroupName;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 描述
     */
    @TableField(value = "descript")
    private String descript;

    /**
     * 组图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 所属分类id
     */
    @TableField(value = "catelog_id")
    private Long catelogId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}