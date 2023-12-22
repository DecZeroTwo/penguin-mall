package com.penguin.penguinmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.pms.Attr;
import com.penguin.penguinmall.product.service.AttrService;
import com.penguin.penguinmall.product.dao.AttrMapper;
import org.springframework.stereotype.Service;

/**
* @author 19654
* @description 针对表【pms_attr(商品属性)】的数据库操作Service实现
* @createDate 2023-12-22 13:16:16
*/
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr>
    implements AttrService{

}




