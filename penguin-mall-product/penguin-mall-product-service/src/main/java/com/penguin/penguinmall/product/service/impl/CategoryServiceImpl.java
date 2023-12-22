package com.penguin.penguinmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.pms.Category;
import com.penguin.penguinmall.product.service.CategoryService;
import com.penguin.penguinmall.product.dao.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 19654
 * @description 针对表【pms_category(商品三级分类)】的数据库操作Service实现
 * @createDate 2023-12-22 13:16:16
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    @Override
    public List<Category> tree() {
        List<Category> categories = baseMapper.selectList(null);
        return categories.stream().filter(category -> category.getParentCid().equals(0L))
                .peek(menu -> menu.setChildren(getChildren(menu, categories)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

    private List<Category> getChildren(Category root, List<Category> categories) {
        List<Category> childen = categories.stream().filter(category -> category.getParentCid().equals(root.getCatId()))
                .peek(category -> category.setChildren(getChildren(category, categories)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
        return childen;
    }
}




