package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "商品大属性")
@RequestMapping("/admin/product")
public class SpuController {

    @Autowired
    SpuInfoService spuInfoService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
    @Autowired
    SpuImageService spuImageService;

    @GetMapping("/{page}/{limit}")
    @ApiOperation(value = "商品的分页")
    public Result pageSPU(@PathVariable("page") Long page,
                          @PathVariable("limit") Long limit,
                          @RequestParam("category3Id") Long category3Id) {

        Page<SpuInfo> spuInfoPage = new Page<>(page, limit);

        QueryWrapper<SpuInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("category3_id", category3Id);

        Page<SpuInfo> infoPage = spuInfoService.page(spuInfoPage, wrapper);
        return Result.ok(infoPage);
    }

    @PostMapping("saveSpuInfo")
    @ApiOperation(value = "商品的新增")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo) {
        spuInfoService.saveSpuInfo(spuInfo);
        return Result.ok();
    }

    @GetMapping("spuImageList/{spuId}")
    @ApiOperation(value = "新增sku时获取图片列表")
    public Result getSpuImage(@PathVariable("spuId") Long spuId) {
        QueryWrapper<SpuImage> wrapper = new QueryWrapper<>();
        wrapper.eq("spu_id", spuId);
        List<SpuImage> spuImages = spuImageService.list(wrapper);
        return Result.ok(spuImages);
    }

    @GetMapping("/spuSaleAttrList/{spuId}")
    @ApiOperation(value = "新增sku时展示销售属性")
    public Result getSpuSaleAttr(@PathVariable("spuId") Long spuId) {
        List<SpuSaleAttr> spuSaleAttrs = spuSaleAttrService.getSpuSaleAttrBySpuId(spuId);
        return Result.ok(spuSaleAttrs);
    }


}
