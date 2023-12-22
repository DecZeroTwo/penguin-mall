package com.penguin.penguinmall.product.dao;

import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19654
* @description 针对表【pms_sku_info(sku信息)】的数据库操作Mapper
* @createDate 2023-12-22 13:16:16
* @Entity com.penguin.penguinmall.domain.entity.po.pms.SkuInfo
*/
@Mapper
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {

}




