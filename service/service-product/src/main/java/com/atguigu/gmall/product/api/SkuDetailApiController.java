package com.atguigu.gmall.product.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.service.SkuImageService;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Api(tags = "商品详情的接口")
@RequestMapping("/api/inner/rpc/product")
@RestController
public class SkuDetailApiController {
    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    SkuImageService skuImageService;
    @Autowired
    BaseCategory3Service baseCategory3Service;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;

    //分成六份  通过池化技术增加性能
    //    @GetMapping("/skudetail/{skuId}")
//   public Result<SkuDetailTo> getSkuDetail(@PathVariable("skuId") Long skuId){
//        SkuDetailTo skuDetailTo = skuInfoService.getSkuDetail(skuId);
//        return Result.ok(skuDetailTo);
//    }
    // 查询sku的基本信息
    @GetMapping("/skudetail/info/{skuId}")
    public Result<SkuInfo> getSkuInfo(@PathVariable("skuId") Long skuId) {
        SkuInfo skuInfo = skuInfoService.getDetailSkuInfo(skuId);
        return Result.ok(skuInfo);
    }

    // 查看图片信息
    @GetMapping("/skudetail/images/{skuId}")
    public Result<List<SkuImage>> getSkuImageList(@PathVariable("skuId") Long skuId) {
        List<SkuImage> imageList = skuImageService.getgetSkuImage(skuId);
        return Result.ok(imageList);
    }

    //商品（sku）所属的完整各级分类信息
    @GetMapping("/skudetail/categoryview/{category3Id}")
    public Result<CategoryViewTo> getCategoryView(@PathVariable Long category3Id) {
        CategoryViewTo categoryViewTo = baseCategory3Service.getCategoryView(category3Id);
        return Result.ok(categoryViewTo);
    }

    //实时价格查询
    @GetMapping("/skudetail/price/{skuId}")
    public Result<BigDecimal> get1010Price(@PathVariable Long skuId) {
        BigDecimal price = skuInfoService.get1010Price(skuId);
        return Result.ok(price);
    }


    //    查sku组合 valueJson
    @GetMapping("/skudetail/valuejson/{spuId}")
    public Result<String> getSKuValueJson(@PathVariable("spuId") Long spuId) {
        String valuejson = spuSaleAttrService.getAllSkuSaleAttrValueJson(spuId);
        return Result.ok(valuejson);
    }


    //    查询sku对应的spu定义的所有销售属性名和值。并且标记出当前sku是哪个
    @GetMapping("/skudetail/saleattrvalues/{skuId}/{spuId}")
    public Result<List<SpuSaleAttr>> getSkuSaleattrvalues(@PathVariable("skuId") Long skuId, @PathVariable("spuId") Long spuId) {
        List<SpuSaleAttr> saleAttrList = spuSaleAttrService
                .getSaleAttrAndValueMarkSku(spuId, skuId);
        return Result.ok(saleAttrList);
    }

}
