package com.penguin.penguinmall.ware.service;

import com.penguin.penguinmall.domain.entity.po.oms.WmsWareSku;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 19654
* @description 针对表【wms_ware_sku(商品库存)】的数据库操作Service
* @createDate 2023-12-25 02:48:02
*/
public interface WmsWareSkuService extends IService<WmsWareSku> {

    void decrease(Long skuId,Integer num);
}
