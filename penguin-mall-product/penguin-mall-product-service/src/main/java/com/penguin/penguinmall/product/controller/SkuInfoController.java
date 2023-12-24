package com.penguin.penguinmall.product.controller;

import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.penguin.penguinmall.product.service.SkuInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "sku模块")
@Slf4j
@RestController
@RequestMapping("/api/skuInfo")
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;

    @GetMapping("/get")
    public HttpResp<SkuInfo> get(Long skuId) {
        SkuInfo skuInfo = skuInfoService.getById(skuId);
        return HttpResp.success(skuInfo);
    }
}
