package com.penguin.penguinmall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.oms.OrderItem;
import com.penguin.penguinmall.order.service.OrderItemService;
import com.penguin.penguinmall.order.dao.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author 19654
* @description 针对表【oms_order_item(订单项信息)】的数据库操作Service实现
* @createDate 2023-12-24 20:22:11
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}




