package com.penguin.penguinmall.domain.entity.po.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 品牌分类关联
 * @TableName pms_category_brand_relation
 */
@TableName(value ="pms_category_brand_relation")
@Data
public class CategoryBrandRelation implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 品牌id
     */
    @TableField(value = "brand_id")
    private Long brandId;

    /**
     * 分类id
     */
    @TableField(value = "catelog_id")
    private Long catelogId;

    /**
     * 
     */
    @TableField(value = "brand_name")
    private String brandName;

    /**
     * 
     */
    @TableField(value = "catelog_name")
    private String catelogName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}