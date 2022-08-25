package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author TomDog
* @description 针对表【base_attr_info(属性表)】的数据库操作Service
* @createDate 2022-08-24 16:13:28
*/
public interface BaseAttrInfoService extends IService<BaseAttrInfo> {

    /*
    * 查询平台属性的方法
    * */
    List<BaseAttrInfo> getAttrInfoAndValueByCategoryId(long category1Id, long category2Id, long category3Id);

    /*
    * 增加平台属性的
    * */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);
}
