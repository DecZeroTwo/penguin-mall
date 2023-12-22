package com.penguin.penguinmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.penguin.penguinmall.product.service.SkuInfoService;
import com.penguin.penguinmall.product.dao.SkuInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 19654
* @description 针对表【pms_sku_info(sku信息)】的数据库操作Service实现
* @createDate 2023-12-22 13:16:16
*/
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo>
    implements SkuInfoService{

}




