package com.penguin.penguinmall.ware.coontroller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.domain.entity.po.oms.WmsWareSku;
import com.penguin.penguinmall.domain.entity.vo.OrderConfirmVo;
import com.penguin.penguinmall.ware.service.WmsWareSkuService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "库存模块")
@Slf4j
@RestController
@RequestMapping("/api/ware")
public class WareSkuController {
    @Autowired
    private WmsWareSkuService wmsWareSkuService;

    @DeleteMapping("/decrease")
    public HttpResp toTrade(Long skuId, Integer num) {
        wmsWareSkuService.decrease(skuId, num);
        return HttpResp.success("减少库存成功");
    }

    @PutMapping("/reduction")
    public HttpResp reduction(Long skuId, Integer num) {
        WmsWareSku wareSku = wmsWareSkuService.getById(skuId);
        wareSku.setStock(wareSku.getStock() + num);
        wmsWareSkuService.updateById(wareSku);
        return HttpResp.success("还原库存成功");
    }
}
