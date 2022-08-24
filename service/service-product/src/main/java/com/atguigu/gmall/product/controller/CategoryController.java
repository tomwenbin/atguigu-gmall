package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.BaseCategory1Service;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/product")

public class CategoryController {

    @Autowired
    BaseCategory1Service baseCategory1Service;
    @Autowired
    BaseCategory2Service baseCategory2Service;
    @Autowired
    BaseCategory3Service baseCategory3Service;

   @GetMapping("/getCategory1")
    public Result getCategory1(){
       List<BaseCategory1> category1List = baseCategory1Service.list();
       return Result.ok(category1List);
   }
   @GetMapping("/getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable long category1Id){
       List<BaseCategory2> list = baseCategory2Service.getCategory1Child(category1Id);
       return Result.ok(list);
   }
    @GetMapping("getCategory3/{category2Id}")
    public Result getCategory3(@PathVariable long category2Id){
        List<BaseCategory3> list = baseCategory3Service.getCategory2Child(category2Id);
        return Result.ok(list);
    }
}
