package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.service.SpuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.service.SpuInfoService;
import com.atguigu.gmall.product.mapper.SpuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* @author TomDog
* @description 针对表【spu_info(商品表)】的数据库操作Service实现
* @createDate 2022-08-24 16:13:27
*/
@Service

public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo>
    implements SpuInfoService{

    @Resource
    SpuInfoMapper spuInfoMapper;
    @Autowired
    SpuImageService spuImageService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
    @Autowired
    SpuSaleAttrValueService spuSaleAttrValueService;

    @Override
    @Transactional
    public void saveSpuInfo(SpuInfo spuInfo) {
        //先对基本信息进行保存
        spuInfoMapper.insert(spuInfo);
        Long spuId = spuInfo.getId();   //获得自增id  也就是后面的 spuId

        //对照片集进行保存
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        for (SpuImage spuImage : spuImageList) {
            //回填spu_id
            spuImage.setSpuId(spuId);
        }
        spuImageService.saveBatch(spuImageList);

        //销售属性 以及销售的属性值    进行嵌套遍历以及相关缺失数据的回填   新增一般用不到sql的语句
        List<SpuSaleAttr> attrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr attr : attrList) {
            attr.setSpuId(spuId);
            String attrName = attr.getSaleAttrName();
            List<SpuSaleAttrValue> attrValueList = attr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue attrValue : attrValueList) {
                attrValue.setSpuId(spuId);
                attrValue.setSaleAttrName(attrName);
            }
            spuSaleAttrValueService.saveBatch(attrValueList);
        }
        spuSaleAttrService.saveBatch(attrList);


    }
}




