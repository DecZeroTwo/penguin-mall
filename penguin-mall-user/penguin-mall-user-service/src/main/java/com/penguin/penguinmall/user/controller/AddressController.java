package com.penguin.penguinmall.user.controller;

import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.domain.entity.po.ums.Address;
import com.penguin.penguinmall.user.service.IAddressService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "地址模块")
@Slf4j
@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private IAddressService ias;

    @GetMapping("/list")
    public HttpResp list(@RequestParam("userId") Long userId) {
        List<Address> address = ias.getAddress(userId);
        return HttpResp.success(address);
    }
}
