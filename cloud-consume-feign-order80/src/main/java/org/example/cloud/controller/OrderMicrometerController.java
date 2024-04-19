package org.example.cloud.controller;

import jakarta.annotation.Resource;
import org.example.cloud.apis.PayFeignApi;
import org.example.cloud.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderMicrometerController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/pay/micrometer/get/{id}")
    public Result<String> myMicrometer(@PathVariable("id") Integer id) {
        return payFeignApi.myMicrometer(id);
    }
}
