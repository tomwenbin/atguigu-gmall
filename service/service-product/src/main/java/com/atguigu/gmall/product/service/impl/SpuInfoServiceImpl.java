package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.SpuInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.service.SpuInfoService;
import com.atguigu.gmall.product.mapper.SpuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {

    }
}




