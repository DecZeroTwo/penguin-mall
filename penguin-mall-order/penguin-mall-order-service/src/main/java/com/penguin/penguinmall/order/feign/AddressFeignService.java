package com.penguin.penguinmall.order.feign;

import com.penguin.penguinmall.common.result.HttpResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("penguin-mall-user")
public interface AddressFeignService {
    @GetMapping(value = "/api/address/list")
    HttpResp getAddress(@RequestParam("userId") Long userId);
}
