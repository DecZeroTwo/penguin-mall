package com.penguin.penguinmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.penguin.penguinmall.domain.entity.po.oms.Order;
import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.penguin.penguinmall.domain.entity.po.ums.Address;
import com.penguin.penguinmall.domain.entity.vo.OrderConfirmVo;

import java.util.List;

/**
 * @author 19654
 * @description 针对表【oms_order(订单)】的数据库操作Service
 * @createDate 2023-12-24 20:22:11
 */
public interface OrderService extends IService<Order> {

    OrderConfirmVo confirmOrder(Long userId);

    void submit(Address address, List<SkuInfo> skuInfoList);

    public void finishOrder(Long orderSn);
}
