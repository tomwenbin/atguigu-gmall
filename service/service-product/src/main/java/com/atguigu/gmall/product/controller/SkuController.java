package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "商品小属性")
@RequestMapping("/admin/product")
public class SkuController {

    @Autowired
    SkuInfoService skuInfoService;

    @GetMapping("/list/{page}/{limit}")
    @ApiOperation(value = "sku商品的分页")
    public Result pageSPU(@PathVariable("page") Long page,
                          @PathVariable("limit") Long limit) {

        Page<SkuInfo> skuInfoPage = new Page<>(page, limit);

        Page<SkuInfo> infoPage = skuInfoService.page(skuInfoPage);
        return Result.ok(infoPage);
    }
    @PostMapping("saveSkuInfo")
    @ApiOperation(value = "sku商品的新增")
    public Result pageSPU1(@RequestBody SkuInfo skuInfo) {

         skuInfoService.saveSkuInfo(skuInfo);
        return Result.ok();
    }
    @GetMapping ("/onSale/{skuId}")
    @ApiOperation(value = "sku商品的上架")
    public Result skuOnSale(@PathVariable Long skuId) {
        skuInfoService.opSkuSale(skuId);
        return Result.ok();
    }
    @GetMapping ("/cancelSale/{skuId}")
    @ApiOperation(value = "sku商品的下架")
    public Result skCancleSale(@PathVariable Long skuId) {
        skuInfoService.CancleSale(skuId);
        return Result.ok();
    }

}
