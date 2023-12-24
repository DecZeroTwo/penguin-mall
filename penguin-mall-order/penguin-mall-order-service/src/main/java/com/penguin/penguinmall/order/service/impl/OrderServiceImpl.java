package com.penguin.penguinmall.order.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.oms.Order;
import com.penguin.penguinmall.domain.entity.po.oms.OrderItem;
import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.penguin.penguinmall.domain.entity.po.ums.Address;
import com.penguin.penguinmall.domain.entity.vo.OrderConfirmVo;
import com.penguin.penguinmall.order.feign.AddressFeignService;
import com.penguin.penguinmall.order.feign.CartFeignService;
import com.penguin.penguinmall.order.feign.SkuInfoFeignService;
import com.penguin.penguinmall.order.service.OrderItemService;
import com.penguin.penguinmall.order.service.OrderService;
import com.penguin.penguinmall.order.dao.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 19654
 * @description 针对表【oms_order(订单)】的数据库操作Service实现
 * @createDate 2023-12-24 20:22:11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AddressFeignService addressFeignService;
    @Autowired
    private CartFeignService cartFeignService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private SkuInfoFeignService skuInfoFeignService;

    @Override
    public OrderConfirmVo confirmOrder(Long userId) {
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        List<Address> address = (List<Address>) addressFeignService.getAddress(userId).getResult();
        List<SkuInfo> cart = (List<SkuInfo>) cartFeignService.getCart(userId).getResult();
        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        orderConfirmVo.setAddresses(address);
        orderConfirmVo.setItems(cart);
        return orderConfirmVo;
    }


    @GlobalTransactional
    @Override
    public void submit(Address address, List<SkuInfo> skuInfoList) {
        Snowflake snowflake = new Snowflake();
        String orderSn = snowflake.nextIdStr();
        Order order = new Order();
        stringRedisTemplate.opsForValue().set("OrderToken:" + address.getUserId(), orderSn, 60, TimeUnit.SECONDS);
        order.setOrderSn(orderSn);
        order.setTotalAmount(BigDecimal.ZERO);
        order.setCreateTime(new Date());
        order.setReceiverName(address.getName());
        order.setReceiverProvince(address.getProvince());
        order.setReceiverRegion(address.getRegion());
        order.setReceiverCity(address.getCity());
        order.setReceiverPhone(address.getPhone());
        order.setReceiverPostCode(address.getPhone());

        for (SkuInfo skuInfo : skuInfoList) {
            SkuInfo result = skuInfoFeignService.getSkuInfo(skuInfo.getSkuId()).getResult();
            order.setTotalAmount(order.getTotalAmount().add(result.getPrice()));
            // TODO 减库存

        }
        baseMapper.insert(order);
    }
}




