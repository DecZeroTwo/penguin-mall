package com.penguin.penguinmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.penguin.penguinmall.domain.entity.po.ums.Address;
import com.penguin.penguinmall.user.dao.AddressDao;
import com.penguin.penguinmall.user.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IAddressServiceImpl implements IAddressService {
    @Autowired
    private AddressDao addressDao;
    @Override
    public List<Address> getAddress(Long userId) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUserId,userId);
        List<Address> addresses = addressDao.selectList(queryWrapper);
        return addresses;
    }
}
