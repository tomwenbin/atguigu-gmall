package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@FeignClient("service-product")
@RequestMapping("/api/inner/rpc/product")

public interface SkuDetailFeignClient {
    //    @GetMapping("/skudetail/{skuId}")
//    Result<SkuDetailTo> getSkuDetail(@PathVariable("skuId") Long skuId);

    @GetMapping("/skudetail/info/{skuId}")
     Result<SkuInfo> getSkuInfo(@PathVariable("skuId") Long skuId);

    @GetMapping("/skudetail/images/{skuId}")
     Result<List<SkuImage>> getSkuImageList(@PathVariable("skuId") Long skuId);

    @GetMapping("/skudetail/categoryview/{category3Id}")
     Result<CategoryViewTo> getCategoryView(@PathVariable Long category3Id);

    @GetMapping("/skudetail/price/{skuId}")
     Result<BigDecimal> get1010Price(@PathVariable Long skuId);

    @GetMapping("/skudetail/valuejson/{spuId}")
     Result<String> getSKuValueJson(@PathVariable("spuId") Long spuId);

    @GetMapping("/skudetail/saleattrvalues/{skuId}/{spuId}")
     Result<List<SpuSaleAttr>> getSkuSaleattrvalues(@PathVariable("skuId") Long skuId, @PathVariable("spuId") Long spuId);
}
