package com.penguin.penguinmall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.penguin.penguinmall.domain.entity.po.oms.Order;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19654
* @description 针对表【oms_order(订单)】的数据库操作Mapper
* @createDate 2023-12-24 20:22:11
* @Entity com.penguin.penguinmall.domain.entity.po.oms.Order
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




