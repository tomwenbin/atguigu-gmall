package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class BaseAttrController {
    @Autowired
    BaseAttrValueService baseAttrValueService;
    @Autowired
    BaseAttrInfoService baseAttrInfoService;
//    /admin/product/attrInfoList/{category1Id}/{category2Id}/{category3Id}
    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result getBaseAttrInfo(@PathVariable("category1Id") long category1Id,
                                  @PathVariable("category2Id") long category2Id,
                                  @PathVariable("category3Id") long category3Id){
        List<BaseAttrInfo> infoList = baseAttrInfoService.getAttrInfoAndValueByCategoryId(category1Id,category2Id,category3Id);
        return Result.ok(infoList);
    }
}
