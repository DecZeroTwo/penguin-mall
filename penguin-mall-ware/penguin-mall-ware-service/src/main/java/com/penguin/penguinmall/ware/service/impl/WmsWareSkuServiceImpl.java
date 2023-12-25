package com.penguin.penguinmall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.oms.WmsWareSku;
import com.penguin.penguinmall.ware.service.WmsWareSkuService;
import com.penguin.penguinmall.ware.mapper.WmsWareSkuMapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 19654
 * @description 针对表【wms_ware_sku(商品库存)】的数据库操作Service实现
 * @createDate 2023-12-25 02:48:02
 */
@Service
public class WmsWareSkuServiceImpl extends ServiceImpl<WmsWareSkuMapper, WmsWareSku>
        implements WmsWareSkuService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void decrease(Long skuId, Integer num) {
        RLock lock = redissonClient.getLock("ware:decrease" + skuId);
        lock.lock();
        try {
            LambdaQueryWrapper<WmsWareSku> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(WmsWareSku::getSkuId, skuId);
            WmsWareSku ware = baseMapper.selectOne(queryWrapper);
            if (ware.getStock() < num) {
                throw new RuntimeException("库存不足");
            } else {
                ware.setStock(ware.getStock() - num);
                baseMapper.updateById(ware);
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            lock.unlock();
        }
        int a = 0;
        a = 1 / 0;
    }
}




