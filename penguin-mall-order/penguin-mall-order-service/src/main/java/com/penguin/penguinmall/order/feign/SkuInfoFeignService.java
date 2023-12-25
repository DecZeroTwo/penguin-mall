package com.penguin.penguinmall.order.feign;

import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("penguin-mall-product")
public interface SkuInfoFeignService {
    @GetMapping(value = "/api/skuInfo/get")
    HttpResp<SkuInfo> getSkuInfo(@RequestParam("skuId") Long skuId);

    @GetMapping(value = "/api/cart/getSelect")
    HttpResp getCart(@RequestParam("userId") Long userId);
}
