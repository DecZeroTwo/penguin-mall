package com.penguin.penguinmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.pms.CommentReplay;
import com.penguin.penguinmall.product.service.CommentReplayService;
import com.penguin.penguinmall.product.dao.CommentReplayMapper;
import org.springframework.stereotype.Service;

/**
* @author 19654
* @description 针对表【pms_comment_replay(商品评价回复关系)】的数据库操作Service实现
* @createDate 2023-12-22 13:16:16
*/
@Service
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayMapper, CommentReplay>
    implements CommentReplayService{

}




