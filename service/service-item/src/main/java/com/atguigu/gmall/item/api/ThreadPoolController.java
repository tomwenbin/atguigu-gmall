package com.atguigu.gmall.item.api;

import com.atguigu.gmall.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

@RestController
@Api(tags = "线程池")
public class ThreadPoolController {

    @Autowired
    ThreadPoolExecutor executor;


    @GetMapping("/close/pool")
    public Result closePool(){
        executor.shutdown(); //关闭线程池
        return Result.ok();
    }

    @GetMapping("/monitor/pool")
    public Result monitorThreadPool(){

        int poolSize = executor.getCorePoolSize();
        long taskCount = executor.getTaskCount();
        return Result.ok(poolSize + "====" + taskCount);
    }


}
