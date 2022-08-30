package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author TomDog
* @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service
* @createDate 2022-08-24 16:13:27
*/
public interface SpuSaleAttrService extends IService<SpuSaleAttr> {


    /*
    * 新增sku时间查询spu的销售属性*/

    List<SpuSaleAttr> getSpuSaleAttrBySpuId(Long spuId);

    List<SpuSaleAttr> getSaleAttrAndValueMarkSku(Long spuId, Long skuId);
    /**
     * 查询所有sku的销售属性值组合可能
     * @param spuId
     * @return
     */
    String getAllSkuSaleAttrValueJson(Long spuId);
}
