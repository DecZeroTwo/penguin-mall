package com.penguin.penguinmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.pms.CategoryBrandRelation;
import com.penguin.penguinmall.product.service.CategoryBrandRelationService;
import com.penguin.penguinmall.product.dao.CategoryBrandRelationMapper;
import org.springframework.stereotype.Service;

/**
* @author 19654
* @description 针对表【pms_category_brand_relation(品牌分类关联)】的数据库操作Service实现
* @createDate 2023-12-22 13:16:16
*/
@Service
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation>
    implements CategoryBrandRelationService{

}




