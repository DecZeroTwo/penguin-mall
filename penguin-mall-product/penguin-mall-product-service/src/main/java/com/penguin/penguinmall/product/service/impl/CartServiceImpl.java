package com.penguin.penguinmall.product.service.impl;

import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.penguin.penguinmall.domain.entity.vo.CartVo;
import com.penguin.penguinmall.product.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    final static String CARTKEYPREFIX = "penguinmall:cart:";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addToCart(Long userId, SkuInfo skuInfo) {
        SkuInfo existSku = (SkuInfo) redisTemplate.opsForHash().get(CARTKEYPREFIX + userId, skuInfo.getSkuId() + "");
        if (Objects.isNull(existSku)) {
            redisTemplate.opsForHash().put(CARTKEYPREFIX + userId, skuInfo.getSkuId() + "", skuInfo);
        } else {
            existSku.setSaleCount(existSku.getSaleCount() + skuInfo.getSaleCount());
            redisTemplate.opsForHash().put(CARTKEYPREFIX + userId, skuInfo.getSkuId() + "", existSku);
        }
    }

    @Override
    public List<SkuInfo> getCart(Long userId) {
        List<Object> values = redisTemplate.opsForHash().values(CARTKEYPREFIX + userId);

        List<SkuInfo> cart = values.stream().map(obj -> {
            SkuInfo skuInfo = (SkuInfo) obj;
            return skuInfo;
        }).collect(Collectors.toList());
        return cart;
    }

    @Override
    public void select(Long userId, Long skuId) {
        SkuInfo skuInfo = (SkuInfo) redisTemplate.opsForHash().get(CARTKEYPREFIX + userId, skuId + "");
        skuInfo.setSelect(true);
        redisTemplate.opsForHash().put(CARTKEYPREFIX + userId, skuId + "", skuInfo);
    }

    @Override
    public void unselect(Long userId, Long skuId) {
        SkuInfo skuInfo = (SkuInfo) redisTemplate.opsForHash().get(CARTKEYPREFIX + userId, skuId + "");
        skuInfo.setSelect(false);
        redisTemplate.opsForHash().put(CARTKEYPREFIX + userId, skuId + "", skuInfo);
    }

    @Override
    public List<SkuInfo> getSelect(Long userId) {
        List<Object> values = redisTemplate.opsForHash().values(CARTKEYPREFIX + userId);

        List<SkuInfo> collect = values.stream().map(obj -> {
            SkuInfo skuInfo = (SkuInfo) obj;
            return skuInfo;
        }).collect(Collectors.toList());

        List<SkuInfo> cart = collect.stream()
                .filter(obj -> obj.getSelect().equals(true))
                .collect(Collectors.toList());
        return cart;
    }
}
