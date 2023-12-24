package com.penguin.penguinmall.domain.entity.vo;

import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import com.penguin.penguinmall.domain.entity.po.ums.Address;
import lombok.*;
import org.springframework.lang.NonNullApi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description: 订单确认页需要用的数据
 * @Created: with IntelliJ IDEA.
 * @author: 夏沫止水
 * @createTime: 2020-07-02 18:59
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmVo {

    /** 会员收获地址列表 **/
    List<Address> addresses;

    /** 所有选中的购物项 **/
    List<SkuInfo> items;
}
