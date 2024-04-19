package org.example.cloud.controller;

import jakarta.annotation.Resource;
import org.example.cloud.apis.PayFeignApi;
import org.example.cloud.entities.PayDTO;
import org.example.cloud.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGatewayController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/pay/gateway/get/{id}")
    public Result<PayDTO> getPayById(@PathVariable("id") Integer id) {
        return payFeignApi.getById4Gateway(id);
    }

    @GetMapping("/pay/gateway/getInfo")
    public Result<String> getInfo() {
        return payFeignApi.getInfo4Gateway();
    }
}
