package com.penguin.penguinmall.order.feign;

import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("penguin-mall-ware")
public interface WareFeignService {
    @DeleteMapping(value = "/api/ware/decrease")
    HttpResp decrease(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num);

    @PutMapping(value = "/api/ware/reduction")
    HttpResp reduction(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num);
}
