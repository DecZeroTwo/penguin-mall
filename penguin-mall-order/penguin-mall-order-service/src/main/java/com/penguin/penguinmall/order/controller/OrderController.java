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

import java.math.BigDecimal;
import java.util.ArrayList;
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
    public HttpResp submit(Address address) {
        ArrayList<SkuInfo> skuInfoArrayList = new ArrayList<>();
        skuInfoArrayList.add(new SkuInfo(1L,11L,"华为 HUAWEI Mate 30 Pro 星河银 8GB+256GB",null,225L,9L,"https://gulimall-hello.oss-cn-beijing.aliyuncs.com/2019-11-26/60e65a44-f943-4ed5-87c8-8cf90f403018_d511faab82abb34b.jpg",
                "华为 HUAWEI Mate 30 Pro 星河银 8GB+256GB麒麟990旗舰芯片OLED环幕屏双4000万徕卡电影四摄4G全网通手机","【现货抢购！享白条12期免息！】麒麟990，OLED环幕屏双4000万徕卡电影四摄；Mate30系列享12期免息》", BigDecimal.valueOf(6299L),1L,true));
        String orderToken = stringRedisTemplate.opsForValue().get("orderToken:" + address.getUserId());
        if (Objects.isNull(orderToken)){
            orderService.submit(address,skuInfoArrayList);
            return HttpResp.success("生成订单成功");
        }
        return HttpResp.failed("请勿重复提交");
    }
}
