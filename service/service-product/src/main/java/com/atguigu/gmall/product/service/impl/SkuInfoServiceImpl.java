package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;
import com.atguigu.gmall.product.mapper.SkuImageMapper;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import com.atguigu.gmall.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
* @author TomDog
* @description 针对表【sku_info(库存单元表)】的数据库操作Service实现
* @createDate 2022-08-24 16:13:27
*/
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo>
    implements SkuInfoService{

    @Resource
    SkuInfoMapper skuInfoMapper;
    @Resource
    BaseCategory3Mapper baseCategory3Mapper;
    @Autowired
    SkuImageService skuImageService;
    @Autowired
    SkuAttrValueService skuAttrValueService;
    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
    @Resource
    SkuImageMapper skuImageMapper;


    @Transactional
    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        //新增sku 基本数据
        save(skuInfo);
//        skuInfoMapper.insert(skuInfo);
        Long skuId = skuInfo.getId();  //查找到新增的后的skuid

        //保存图片的信息
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        for (SkuImage image : skuImageList) {
            image.setSkuId(skuId);
        }
        skuImageService.saveBatch(skuImageList);

        //保存当前sku平台属性名和值对应关系
        List<SkuAttrValue> attrValueList = skuInfo.getSkuAttrValueList();
        for (SkuAttrValue attrValue : attrValueList) {
            attrValue.setSkuId(skuId);
        }
        skuAttrValueService.saveBatch(attrValueList);

        //4、sku的销售属性名和值的关系保存到 sku_sale_attr_value
        List<SkuSaleAttrValue> attrSaleValueList = skuInfo.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue saleAttrValue : attrSaleValueList) {
            saleAttrValue.setSkuId(skuId);
            saleAttrValue.setSpuId(skuInfo.getSpuId());
        }
        skuSaleAttrValueService.saveBatch(attrSaleValueList);


    }

    @Override
    public void opSkuSale(Long skuId) {
        skuInfoMapper.updateOnSale(skuId,1);   //1 是上架  2 是下架
    }

    @Override
    public void CancleSale(Long skuId) {
        skuInfoMapper.updateOnSale(skuId,0);
    }

    @Override
    public BigDecimal get1010Price(Long skuId) {
        //性能低下
        BigDecimal price = skuInfoMapper.getRealPrice(skuId);
        return price;
    }

    @Override
    public SkuInfo getDetailSkuInfo(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        return skuInfo;
    }

    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {
        SkuDetailTo detailTo = new SkuDetailTo();
        //(√) 0、查询到skuInfo
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);

        //(√) 2、商品（sku）的基本信息【价格、重量、名字...】   sku_info
        //把查询到的数据一定放到 SkuDetailTo 中
        detailTo.setSkuInfo(skuInfo);

        //(√) 3、商品（sku）的图片        sku_image
        //        List<SkuImage> imageList22 = skuImageMapper.getgetSkuImage22(skuId);
        List<SkuImage> imageList =skuImageService.getgetSkuImage(skuId);
        skuInfo.setSkuImageList(imageList);

        //(√) 1、商品（sku）所属的完整分类信息：  base_category1、base_category2、base_category3
        CategoryViewTo categoryViewTo =  baseCategory3Mapper.getCategoryView(  skuInfo.getCategory3Id());
        detailTo.setCategoryView(categoryViewTo);

        //(√) 实时价格查询
        BigDecimal price = get1010Price(skuId);
        detailTo.setPrice(price);

        //(√)4、商品（sku）所属的SPU当时定义的所有销售属性名值组合（固定好顺序）。
        //          spu_sale_attr、spu_sale_attr_value
        //          并标识出当前sku到底spu的那种组合，页面要有高亮框 sku_sale_attr_value
        //查询当前sku对应的spu定义的所有销售属性名和值（固定好顺序）并且标记好当前sku属于哪一种组合
//        SkuInfoService.getSaleAttrAndValueMarkSku()
        Long spuId = skuInfo.getSpuId();
        List<SpuSaleAttr> saleAttrList = spuSaleAttrService.getSaleAttrAndValueMarkSku(spuId,skuId);
         detailTo.setSpuSaleAttrList(saleAttrList);

        //(√)5、商品（sku）的所有兄弟产品的销售属性名和值组合关系全部查出来，并封装成
        // {"118|120": "50","119|121": 50} 这样的json字符串
        String valuejson = spuSaleAttrService.getAllSkuSaleAttrValueJson(spuId);
        detailTo.setValuesSkuJson(valuejson);

        return detailTo;
    }

}




