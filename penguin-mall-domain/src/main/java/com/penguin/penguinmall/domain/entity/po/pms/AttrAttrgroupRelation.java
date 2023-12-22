package com.penguin.penguinmall.domain.entity.po.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 属性&属性分组关联
 * @TableName pms_attr_attrgroup_relation
 */
@TableName(value ="pms_attr_attrgroup_relation")
@Data
public class AttrAttrgroupRelation implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 属性id
     */
    @TableField(value = "attr_id")
    private Long attrId;

    /**
     * 属性分组id
     */
    @TableField(value = "attr_group_id")
    private Long attrGroupId;

    /**
     * 属性组内排序
     */
    @TableField(value = "attr_sort")
    private Integer attrSort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}