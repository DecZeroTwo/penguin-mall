package com.penguin.penguinmall.product.dao;

import com.penguin.penguinmall.domain.entity.po.pms.ProductAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19654
* @description 针对表【pms_product_attr_value(spu属性值)】的数据库操作Mapper
* @createDate 2023-12-22 13:16:16
* @Entity com.penguin.penguinmall.domain.entity.po.pms.ProductAttrValue
*/
@Mapper
public interface ProductAttrValueMapper extends BaseMapper<ProductAttrValue> {

}




