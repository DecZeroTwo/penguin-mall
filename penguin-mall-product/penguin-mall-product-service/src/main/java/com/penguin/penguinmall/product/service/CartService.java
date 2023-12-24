package com.penguin.penguinmall.product.service;

import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.penguin.penguinmall.domain.entity.vo.CartVo;

import java.util.List;

public interface CartService {
    void addToCart(Long userId, SkuInfo skuInfo);

    List<SkuInfo> getCart(Long userId);

    void select(Long userId, Long skuId);

    void unselect(Long userId, Long skuId);

    List<SkuInfo> getSelect(Long userId);
}
