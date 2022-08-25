package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    //平台属性的增加  和修改的二合一
    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
          baseAttrInfoService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }
    //根据平台属性ID获取平台属性对象数据
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueById(@PathVariable long attrId){
        List<BaseAttrValue> attrValueList = baseAttrValueService.getAttrValueById(attrId);
        return  Result.ok(attrValueList);
    }
}
