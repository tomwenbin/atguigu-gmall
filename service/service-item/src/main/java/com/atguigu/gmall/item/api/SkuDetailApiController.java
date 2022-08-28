package com.atguigu.gmall.item.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.model.to.SkuDetailTo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "商品详情的接口")
@RequestMapping("/api/inner/rpc/item")
public class SkuDetailApiController {

    @Resource
    SkuDetailService skuDetailService;
    @GetMapping("skudetail/{skuId}")
    public Result<SkuDetailTo> getSkuDetail(@PathVariable Long skuId){
         SkuDetailTo skuDetai = skuDetailService.getSkuDetail(skuId);
//        一些信息
        return Result.ok(skuDetai);
    }
}
