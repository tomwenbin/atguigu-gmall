package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class BaseTrademarkController {
// 品牌的分页
    @Autowired
    BaseTrademarkService  baseTrademarkService;

    @GetMapping("baseTrademark/{page}/{limit}")
    public Result pageBaseTrademark(@PathVariable("page") Long page,
                                    @PathVariable("limit") Long limit){
        Page<BaseTrademark> trademarkPage =  new Page<>(page,limit);

        Page<BaseTrademark> page1 = baseTrademarkService.page(trademarkPage);
        return Result.ok(page1);
    }
// 品牌的添加
    @PostMapping("/baseTrademark/save")
    public Result saveTrademark(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }
//品牌的修改1.1  数据进行回显
    @GetMapping("/baseTrademark/get/{id}")
    public Result getTrademark(@PathVariable Long id){
        BaseTrademark id1 = baseTrademarkService.getById(id);
        return Result.ok(id1);
    }
//品牌的修改
    @PutMapping("/baseTrademark/update")
    public Result updateTrademark(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }
//品牌的删除
    @DeleteMapping("/baseTrademark/remove/{id}")
    public Result updateTrademark(@PathVariable Long id){
        baseTrademarkService.removeById(id);
        return Result.ok();
    }
    //  获取所有品牌
    @GetMapping("/baseTrademark/getTrademarkList")
    public Result getTrademarkList(){
        List<BaseTrademark> list = baseTrademarkService.list();
        return Result.ok(list);
    }
}
