package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author TomDog
* @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
* @createDate 2022-08-24 16:13:28
*/
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
    implements BaseAttrInfoService{

    @Resource
     BaseAttrInfoMapper baseAttrInfoMapper;

    @Override
    public List<BaseAttrInfo> getAttrInfoAndValueByCategoryId( long category1Id,
                                                              long category2Id,
                                                              long category3Id) {
        List<BaseAttrInfo> infos = baseAttrInfoMapper.getAttrInfoAndValueByCategoryId(category1Id,category2Id,category3Id);
        return infos;
    }
}




