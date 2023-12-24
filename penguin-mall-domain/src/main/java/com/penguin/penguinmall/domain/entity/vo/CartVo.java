package com.penguin.penguinmall.domain.entity.vo;

import com.penguin.penguinmall.domain.entity.po.pms.SkuInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVo {
    List<SkuInfo> skuInfoList;
}
