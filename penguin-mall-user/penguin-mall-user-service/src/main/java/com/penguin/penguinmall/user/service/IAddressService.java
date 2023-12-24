package com.penguin.penguinmall.user.service;


import com.penguin.penguinmall.domain.entity.po.ums.Address;

import java.util.List;

public interface IAddressService {
    List<Address> getAddress(Long userId);
}
