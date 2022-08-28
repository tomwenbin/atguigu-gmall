package com.atguigu.gmall.product.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "三级分类的RPC接口")
@RequestMapping("/api/inner/rpc/product")
@RestController
public class CatrgoryApiController {

    @Autowired
    BaseCategory2Service baseCategory2Service;
    /**
     * 查询所有分类并封装成树形菜单结构
     */
    @ApiOperation("三级分类树形结构查询")
    @GetMapping("/category/tree")
//    /api/inner/rpc/product/category/tree
    public Result getAllCategoryWithTree(){

        List<CategoryTreeTo> categoryTreeTos = baseCategory2Service.getAllCategoryWithTree();

        return Result.ok(categoryTreeTos);
    }

}
