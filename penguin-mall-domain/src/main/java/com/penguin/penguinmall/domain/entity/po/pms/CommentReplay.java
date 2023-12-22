package com.penguin.penguinmall.domain.entity.po.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品评价回复关系
 * @TableName pms_comment_replay
 */
@TableName(value ="pms_comment_replay")
@Data
public class CommentReplay implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论id
     */
    @TableField(value = "comment_id")
    private Long commentId;

    /**
     * 回复id
     */
    @TableField(value = "reply_id")
    private Long replyId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}