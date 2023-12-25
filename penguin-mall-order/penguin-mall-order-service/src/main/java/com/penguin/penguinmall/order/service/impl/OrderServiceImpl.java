package com.penguin.penguinmall.order.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.oms.Order;
import com.penguin.penguinmall.domain.entity.po.oms.OrderItem;
import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.penguin.penguinmall.domain.entity.po.ums.Address;
import com.penguin.penguinmall.domain.entity.vo.OrderConfirmVo;
import com.penguin.penguinmall.mq.constants.RabbitmqConstants;
import com.penguin.penguinmall.order.feign.AddressFeignService;
import com.penguin.penguinmall.order.feign.SkuInfoFeignService;
import com.penguin.penguinmall.order.feign.WareFeignService;
import com.penguin.penguinmall.order.service.OrderItemService;
import com.penguin.penguinmall.order.service.OrderService;
import com.penguin.penguinmall.order.dao.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    final static String CARTKEYPREFIX = "penguinmall:cart:";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AddressFeignService addressFeignService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private SkuInfoFeignService skuInfoFeignService;
    @Autowired
    private WareFeignService wareFeignService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public OrderConfirmVo confirmOrder(Long userId) {
        List<Address> address = (List<Address>) addressFeignService.getAddress(userId).getResult();
        List<SkuInfo> cart = (List<SkuInfo>) skuInfoFeignService.getCart(userId).getResult();
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
            wareFeignService.decrease(skuInfo.getSkuId(), Integer.valueOf(skuInfo.getSaleCount().toString()));
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(skuInfo.getSkuId());
            orderItem.setSkuPrice(skuInfo.getPrice());
            orderItem.setOrderSn(orderSn);
            orderItem.setSkuQuantity(Integer.valueOf(skuInfo.getSaleCount().toString()));
            orderItem.setCategoryId(skuInfo.getCatalogId());
            orderItem.setCategoryId(skuInfo.getCatalogId());
            orderItem.setSpuId(skuInfo.getSpuId());
            orderItem.setSkuName(skuInfo.getSkuName());
            orderItemService.save(orderItem);
            redisTemplate.opsForHash().delete(CARTKEYPREFIX + address.getUserId(), skuInfo.getSkuId() + "");
        }
        order.setStatus(0);
        baseMapper.insert(order);
        int delayTime = 1000 * 60 * 1;
        rabbitTemplate.convertAndSend(
                RabbitmqConstants.DELAY_PLUGIN_EXCHANGE,
                RabbitmqConstants.DELAY_PLUGIN_ROUTING_KEY,
                order.getOrderSn(),
                ppm -> {
                    ppm.getMessageProperties().setDelay(delayTime);
                    return ppm;
                }
        );



        stringRedisTemplate.delete("OrderToken:" + address.getUserId());
    }

    @RabbitListener(queues = RabbitmqConstants.SSC_DELAY_PLUGIN_QUEUE)
    @GlobalTransactional
    @Override
    public void finishOrder(Long orderSn) {
        LambdaQueryWrapper<Order> orderLambdaQueryWrapperqueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapperqueryWrapper.eq(Order::getOrderSn, orderSn);

        Order order = baseMapper.selectOne(orderLambdaQueryWrapperqueryWrapper);

        if (order.getStatus() == 0) {
            order.setStatus(5);
            LambdaQueryWrapper<OrderItem> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(OrderItem::getOrderSn, orderSn);
            List<OrderItem> list = orderItemService.list(queryWrapper);
            for (OrderItem orderItem : list) {
                wareFeignService.reduction(orderItem.getSkuId(), orderItem.getSkuQuantity());
            }
            baseMapper.updateById(order);
        }
    }
}




