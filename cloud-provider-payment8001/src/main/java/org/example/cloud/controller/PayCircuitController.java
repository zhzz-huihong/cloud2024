package org.example.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.example.cloud.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PayCircuitController {
    @GetMapping("/pay/circuit/{id}")
    public Result<String> myCircuit(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("Id不能为负数 ... ");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Result.success("Hello" + id + IdUtil.simpleUUID());
    }

    @GetMapping("/pay/bulkhead/{id}")
    public Result<String> myBulkhead(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("Id不能为负数 ... ");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Result.success("Hello" + id + IdUtil.simpleUUID());
    }

    @GetMapping("/pay/rateLimit/{id}")
    public Result<String> myRateLimit(@PathVariable("id") Integer id) {
        return Result.success("Hello" + id + IdUtil.simpleUUID());
    }
}
