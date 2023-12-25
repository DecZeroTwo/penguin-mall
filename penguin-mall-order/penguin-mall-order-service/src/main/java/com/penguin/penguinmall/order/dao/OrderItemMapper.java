package com.penguin.penguinmall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.penguin.penguinmall.domain.entity.po.oms.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19654
* @description 针对表【oms_order_item(订单项信息)】的数据库操作Mapper
* @createDate 2023-12-24 20:22:11
* @Entity com.penguin.penguinmall.domain.entity.po.oms.OrderItem
*/
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}




