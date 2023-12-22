package com.penguin.penguinmall.product.dao;

import com.penguin.penguinmall.domain.entity.po.pms.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19654
* @description 针对表【pms_category(商品三级分类)】的数据库操作Mapper
* @createDate 2023-12-22 13:16:16
* @Entity com.penguin.penguinmall.domain.entity.po.pms.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




