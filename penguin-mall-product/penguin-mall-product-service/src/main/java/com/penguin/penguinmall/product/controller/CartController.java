package com.penguin.penguinmall.product.controller;


import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.penguin.penguinmall.product.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "购物车模块")
@Slf4j
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @ApiOperation(value = "add", notes = "添加商品到购物车")
    @GetMapping("/add")
    public HttpResp add(Long userId, SkuInfo skuInfo) {
        cartService.addToCart(userId, skuInfo);
        return HttpResp.success("添加成功");
    }

    @ApiOperation(value = "select", notes = "选中购物车商品")
    @GetMapping("/select")
    public HttpResp select(Long userId, Long skuId) {
        cartService.select(userId,skuId);
        return HttpResp.success("选中成功");
    }

    @ApiOperation(value = "unSelect", notes = "取消选中购物车商品")
    @GetMapping("/unSelect")
    public HttpResp unSelect(Long userId, Long skuId) {
        cartService.unselect(userId,skuId);
        return HttpResp.success("取消选中成功");
    }

    @ApiOperation(value = "get", notes = "获取购物车")
    @GetMapping("/get")
    public HttpResp get(Long userId) {
        List<SkuInfo> cart = cartService.getCart(userId);
        return HttpResp.success(cart);
    }

    @ApiOperation(value = "getSelect", notes = "获取购物车选中商品")
    @GetMapping("/getSelect")
    public HttpResp getSelect(Long userId) {
        List<SkuInfo> cart = cartService.getSelect(userId);
        return HttpResp.success(cart);
    }
}
