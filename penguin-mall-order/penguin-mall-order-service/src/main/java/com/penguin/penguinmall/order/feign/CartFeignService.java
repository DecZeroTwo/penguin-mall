package com.penguin.penguinmall.order.feign;

import com.penguin.penguinmall.common.result.HttpResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("penguin-mall-product")
public interface CartFeignService {
    @GetMapping(value = "/api/cart/getSelect")
    HttpResp getCart(@RequestParam("userId") Long userId);
}
