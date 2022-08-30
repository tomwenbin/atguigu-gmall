package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.ValueSkuJsonTo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author TomDog
* @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service实现
* @createDate 2022-08-24 16:13:27
*/
@Service
public class SpuSaleAttrServiceImpl extends ServiceImpl<SpuSaleAttrMapper, SpuSaleAttr>
    implements SpuSaleAttrService{

    @Resource
    SpuSaleAttrMapper spuSaleAttrMapper;

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrBySpuId(Long spuId) {
        List<SpuSaleAttr> saleAttrList = spuSaleAttrMapper.getSpuSaleAttrAndValueBySpuId(spuId);
        return saleAttrList;
    }

    @Override
    public List<SpuSaleAttr> getSaleAttrAndValueMarkSku(Long spuId, Long skuId) {
        return    spuSaleAttrMapper.getSaleAttrAndValueMarkSku(spuId,spuId);

    }

    @Override
    public String getAllSkuSaleAttrValueJson(Long spuId) {
        // {"118|120":49,"119|121":50}
        // StreamAPI  lambda；
        Map<String, Long> map = new HashMap<>();
        List<ValueSkuJsonTo> jsonTos = spuSaleAttrMapper.getAllSkuSaleAttrValueJson(spuId);
        for (ValueSkuJsonTo jsonTo : jsonTos) {
            String valueJson = jsonTo.getValueJson(); // 118|120
            Long skuId = jsonTo.getSkuId(); // 49
            map.put(valueJson, skuId);
        }
        //fastjson  springboot: jackson
        String json = Jsons.toStr(map);
        return json;
    }
}




