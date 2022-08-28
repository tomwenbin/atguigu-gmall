package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
* @author TomDog
* @description 针对表【sku_info(库存单元表)】的数据库操作Service
* @createDate 2022-08-24 16:13:27
*/
public interface SkuInfoService extends IService<SkuInfo> {

     /*
     *
     * 新增
     * */
    void saveSkuInfo(SkuInfo skuInfo);

    /*
     *
     * 上下架
     * */
    void opSkuSale(Long skuId);

    void CancleSale(Long skuId);

    /*
    * 查询商品详情
    * */
    SkuDetailTo getSkuDetail(Long skuId);

    /*
    *查询实时价格
    * */
    BigDecimal get1010Price(Long skuId);

}
