package com.penguin.penguinmall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19654
* @description 针对表【oms_order_setting(订单配置信息)】的数据库操作Mapper
* @createDate 2023-12-24 20:22:11
* @Entity com.penguin.penguinmall.domain.entity.po.oms.OrderSetting
*/
@Mapper
public interface OrderSettingMapper extends BaseMapper<OrderSetting> {

}




