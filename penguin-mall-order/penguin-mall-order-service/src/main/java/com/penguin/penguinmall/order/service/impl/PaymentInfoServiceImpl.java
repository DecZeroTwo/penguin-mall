package com.penguin.penguinmall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.penguinmall.domain.entity.po.oms.PaymentInfo;
import com.penguin.penguinmall.order.service.PaymentInfoService;
import com.penguin.penguinmall.order.dao.PaymentInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 19654
* @description 针对表【oms_payment_info(支付信息表)】的数据库操作Service实现
* @createDate 2023-12-24 20:22:11
*/
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo>
    implements PaymentInfoService{

}




