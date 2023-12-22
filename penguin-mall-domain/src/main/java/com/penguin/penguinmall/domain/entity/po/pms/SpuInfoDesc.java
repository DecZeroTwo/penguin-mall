package com.penguin.penguinmall.domain.entity.po.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * spu信息介绍
 * @TableName pms_spu_info_desc
 */
@TableName(value ="pms_spu_info_desc")
@Data
public class SpuInfoDesc implements Serializable {
    /**
     * 商品id
     */
    @TableId(value = "spu_id")
    private Long spuId;

    /**
     * 商品介绍
     */
    @TableField(value = "decript")
    private String decript;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}