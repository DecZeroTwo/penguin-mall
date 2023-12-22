package com.penguin.penguinmall.product.service;

import com.penguin.penguinmall.domain.entity.po.pms.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 19654
* @description 针对表【pms_category(商品三级分类)】的数据库操作Service
* @createDate 2023-12-22 13:16:16
*/
public interface CategoryService extends IService<Category> {
    List<Category> tree();
}
