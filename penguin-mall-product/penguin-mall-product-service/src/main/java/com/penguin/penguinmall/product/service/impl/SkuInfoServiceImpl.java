package com.penguin.penguinmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.pms.Category;
import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.penguin.penguinmall.product.dao.CategoryMapper;
import com.penguin.penguinmall.product.dao.SkuInfoMapper;
import com.penguin.penguinmall.product.service.CategoryService;
import com.penguin.penguinmall.product.service.SkuInfoService;
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
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo>
        implements SkuInfoService {
}




