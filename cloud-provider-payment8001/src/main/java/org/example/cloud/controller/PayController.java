package org.example.cloud.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cloud.entities.Pay;
import org.example.cloud.entities.PayDTO;
import org.example.cloud.response.Result;
import org.example.cloud.response.ReturnCodeEnum;
import org.example.cloud.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "订单CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增支付流水, 参数是JSON字符串")
    public Result<Integer> addPay(@RequestBody PayDTO payDTO) {
        try {
            Pay pay = new Pay();
            BeanUtils.copyProperties(payDTO, pay);
            return Result.success(payService.add(pay));
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水, 参数是Id")
    public Result<Integer> deletePay(@PathVariable("id") Integer id) {
        try {
            return Result.success(payService.delete(id));
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "更新", description = "更新支付流水, 参数是JSON字符串, 根据Id更新")
    public Result<Integer> updatePay(@RequestBody PayDTO payDTO) {
        try {
            Pay pay = new Pay();
            BeanUtils.copyProperties(payDTO, pay);
            return Result.success(payService.update(pay));
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "查询单个", description = "查询支付流水, 参数是Id")
    public Result<Pay> getById(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("id不能小于0");

        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            return Result.fail(e.getMessage());
        }
        return Result.success(payService.getById(id));
    }

    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "查询所有", description = "查询所有支付流水")
    public Result<List<Pay>> getAll() {
        return Result.success(payService.getAll());
    }


    @GetMapping(value = "/pay/error")
    @Operation(summary = "抛出异常测试", description = "抛出异常")
    public Result<List<Pay>> error() {
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            return Result.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return Result.success(payService.getAll());
    }


    @Value("${server.port}")
    private String port;

    @GetMapping("/pay/getInfo")
    public Result gerInfo(@Value("${haoren.info}") String info) {
        return Result.success("info : " + info + " port : " + port);
    }
}
