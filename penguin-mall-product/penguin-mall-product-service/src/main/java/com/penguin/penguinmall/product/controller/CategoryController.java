package com.penguin.penguinmall.product.controller;

import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.domain.entity.po.pms.Category;
import com.penguin.penguinmall.product.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "商品模块")
@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService ics;

    @GetMapping("/tree")
    public HttpResp<List<Category>> list() {
        List<Category> list = ics.tree();
        return HttpResp.success(list);
    }
}
