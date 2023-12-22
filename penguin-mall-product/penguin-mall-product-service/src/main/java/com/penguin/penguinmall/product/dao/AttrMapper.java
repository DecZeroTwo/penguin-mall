package com.penguin.penguinmall.product.dao;

import com.penguin.penguinmall.domain.entity.po.pms.Attr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19654
* @description 针对表【pms_attr(商品属性)】的数据库操作Mapper
* @createDate 2023-12-22 13:16:16
* @Entity com.penguin.penguinmall.domain.entity.po.pms.Attr
*/
@Mapper
public interface AttrMapper extends BaseMapper<Attr> {

}




