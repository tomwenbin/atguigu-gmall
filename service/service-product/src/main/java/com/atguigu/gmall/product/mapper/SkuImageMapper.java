package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.SkuImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author TomDog
* @description 针对表【sku_image(库存单元图片表)】的数据库操作Mapper
* @createDate 2022-08-24 16:13:27
* @Entity com.atguigu.gmall.product.domain.SkuImage
*/
public interface SkuImageMapper extends BaseMapper<SkuImage> {



    List<SkuImage> getgetSkuImage22(@Param("skuId") Long skuId);
}




