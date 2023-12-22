package com.penguin.penguinmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.pms.ProductAttrValue;
import com.penguin.penguinmall.product.service.ProductAttrValueService;
import com.penguin.penguinmall.product.dao.ProductAttrValueMapper;
import org.springframework.stereotype.Service;

/**
* @author 19654
* @description 针对表【pms_product_attr_value(spu属性值)】的数据库操作Service实现
* @createDate 2023-12-22 13:16:16
*/
@Service
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValue>
    implements ProductAttrValueService{

}




