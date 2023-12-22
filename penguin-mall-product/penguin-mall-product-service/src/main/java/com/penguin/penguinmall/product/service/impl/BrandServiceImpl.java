package com.penguin.penguinmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.pms.Brand;
import com.penguin.penguinmall.product.service.BrandService;
import com.penguin.penguinmall.product.dao.BrandMapper;
import org.springframework.stereotype.Service;

/**
* @author 19654
* @description 针对表【pms_brand(品牌)】的数据库操作Service实现
* @createDate 2023-12-22 13:16:16
*/
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand>
    implements BrandService{

}




