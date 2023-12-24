package com.penguin.penguinmall.product.controller;

import com.penguin.penguinmall.common.result.HttpResp;
import com.penguin.penguinmall.domain.entity.po.pms.Category;
import com.penguin.penguinmall.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(tags = "商品分类模块")
@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "tree", notes = "三级分类树形结构")
    @GetMapping("/tree")
    public HttpResp<List<Category>> list() {
        List<Category> list = categoryService.tree();
        return HttpResp.success(list);
    }

    @ApiOperation(value = "save", notes = "新增分类")
    @PostMapping("/save")
    public HttpResp save(@RequestBody Category category) {
        boolean save = categoryService.save(category);
        return HttpResp.success(save);
    }

    @ApiOperation(value = "delete", notes = "逻辑删除分类")
    @DeleteMapping("/delete")
    public HttpResp delete(@RequestBody Long[] catIds) {
        categoryService.removeMenuByIds(Arrays.asList(catIds));
        return HttpResp.success("删除成功");
    }

    @ApiOperation(value = "info", notes = "获取当前信息")
    @GetMapping("/info")
    public HttpResp info(Long catId) {
        Category category = categoryService.getById(catId);
        return HttpResp.success(category);
    }

    @PutMapping("/update")
    public HttpResp update(@RequestBody Category category){
        categoryService.updateById(category);
        return HttpResp.success("更新成功");
    }
}
