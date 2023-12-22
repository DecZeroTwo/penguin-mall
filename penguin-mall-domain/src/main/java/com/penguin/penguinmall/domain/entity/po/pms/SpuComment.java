package com.penguin.penguinmall.domain.entity.po.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品评价
 * @TableName pms_spu_comment
 */
@TableName(value ="pms_spu_comment")
@Data
public class SpuComment implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * sku_id
     */
    @TableField(value = "sku_id")
    private Long skuId;

    /**
     * spu_id
     */
    @TableField(value = "spu_id")
    private Long spuId;

    /**
     * 商品名字
     */
    @TableField(value = "spu_name")
    private String spuName;

    /**
     * 会员昵称
     */
    @TableField(value = "member_nick_name")
    private String memberNickName;

    /**
     * 星级
     */
    @TableField(value = "star")
    private Integer star;

    /**
     * 会员ip
     */
    @TableField(value = "member_ip")
    private String memberIp;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 显示状态[0-不显示，1-显示]
     */
    @TableField(value = "show_status")
    private Integer showStatus;

    /**
     * 购买时属性组合
     */
    @TableField(value = "spu_attributes")
    private String spuAttributes;

    /**
     * 点赞数
     */
    @TableField(value = "likes_count")
    private Integer likesCount;

    /**
     * 回复数
     */
    @TableField(value = "reply_count")
    private Integer replyCount;

    /**
     * 评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
     */
    @TableField(value = "resources")
    private String resources;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 用户头像
     */
    @TableField(value = "member_icon")
    private String memberIcon;

    /**
     * 评论类型[0 - 对商品的直接评论，1 - 对评论的回复]
     */
    @TableField(value = "comment_type")
    private Integer commentType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}