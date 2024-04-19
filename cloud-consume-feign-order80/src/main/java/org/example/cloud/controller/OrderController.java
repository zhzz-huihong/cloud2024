package org.example.cloud.controller;

import cn.hutool.core.date.DateUtil;
import jakarta.annotation.Resource;
import org.example.cloud.apis.PayFeignApi;
import org.example.cloud.entities.PayDTO;
import org.example.cloud.response.Result;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public Result addOrder(@RequestBody PayDTO payDTO){
        return payFeignApi.addPay(payDTO);
    }

    @DeleteMapping("/feign/pay/del/{id}")
    public Result delOrder(@PathVariable("id") Integer id){
        return payFeignApi.deletePay(id);
    }

    @PutMapping("/feign/pay/update")
    public Result updateOrder(@RequestBody PayDTO payDTO){
        return payFeignApi.updatePay(payDTO);
    }

    @GetMapping("/feign/pay/get/{id}")
    public Result getOrderById(@PathVariable("id") Integer id){
        Result result = null;
        try {
            System.out.println("调用时间： " + DateUtil.now());
            result = payFeignApi.getById(id);
        } catch (Exception e) {
            System.out.println("调用时间：" + DateUtil.now());
            return Result.fail(e.getMessage());
        }
        return result;
    }

    @GetMapping("/feign/pay/getAll")
    public Result getAllOrder(){
        return payFeignApi.getAll();
    }

    @GetMapping("/feign/pay/getInfo")
    public Result getInfo(){
        return payFeignApi.getInfo();
    }

}
