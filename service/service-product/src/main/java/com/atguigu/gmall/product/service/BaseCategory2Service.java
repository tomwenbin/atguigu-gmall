package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author TomDog
* @description 针对表【base_category2(二级分类表)】的数据库操作Service
* @createDate 2022-08-24 00:12:57
*/

public interface BaseCategory2Service extends IService<BaseCategory2> {

    /**
     *  查询一级分类对应下的二级分类
     *  方法注释的用法
     */
    List<BaseCategory2> getCategory1Child(long category1Id);

    /*
    *远程调用的  查村所有的分类
    * */
    List<CategoryTreeTo> getAllCategoryWithTree();

}
