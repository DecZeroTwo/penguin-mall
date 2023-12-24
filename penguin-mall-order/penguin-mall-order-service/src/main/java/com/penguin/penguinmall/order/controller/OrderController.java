package com.penguin.penguinmall.order.controller;

import cn.hutool.core.lang.Snowflake;
import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.domain.entity.po.oms.Order;
import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.penguin.penguinmall.domain.entity.po.ums.Address;
import com.penguin.penguinmall.domain.entity.vo.OrderConfirmVo;
import com.penguin.penguinmall.order.service.OrderService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Api(tags = "订单模块")
@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private OrderService orderService;

    @GetMapping("/toTrade")
    public HttpResp toTrade(Long userId) {
        OrderConfirmVo confirmVo = orderService.confirmOrder(userId);
        return HttpResp.success(confirmVo);
    }

    @PostMapping("/submit")
    public HttpResp submit(Address address, List<SkuInfo> skuInfoList) {
        String orderToken = stringRedisTemplate.opsForValue().get("orderToken:" + address.getUserId());
        if (Objects.nonNull(orderToken)){
            orderService.submit(address,skuInfoList);
            return HttpResp.success("生成订单成功");
        }
        return HttpResp.failed("请勿重复提交");
    }
}
