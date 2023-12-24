package com.penguin.penguinmall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19654
* @description 针对表【oms_order_operate_history(订单操作历史记录)】的数据库操作Mapper
* @createDate 2023-12-24 20:22:11
* @Entity com.penguin.penguinmall.domain.entity.po.oms.OrderOperateHistory
*/
@Mapper
public interface OrderOperateHistoryMapper extends BaseMapper<OrderOperateHistory> {

}




