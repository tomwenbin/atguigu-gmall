package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author TomDog
* @description 针对表【base_attr_value(属性值表)】的数据库操作Service
* @createDate 2022-08-24 16:13:27
*/
public interface BaseAttrValueService extends IService<BaseAttrValue> {

    //根据平台属性ID获取平台属性对象数据
    List<BaseAttrValue> getAttrValueById(long attrId);
}
