package org.example.cloud.controller;

import cn.hutool.core.util.IdUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.cloud.entities.PayDTO;
import org.example.cloud.response.Result;
import org.example.cloud.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
@Slf4j
@RestController
public class PayGatewayController {
    @Resource
    private PayService payService;

    @GetMapping("/pay/gateway/get/{id}")
    @Operation(summary = "查询单个", description = "查询支付流水, 参数是Id")
    Result<PayDTO> getById4Gateway(@PathVariable("id") Integer id) {
        PayDTO payDTO = new PayDTO();
        BeanUtils.copyProperties(payService.getById(id), payDTO);
        return Result.success(payDTO);
    }

    @GetMapping("/pay/gateway/getInfo")
    Result<String> getInfo4Gateway(){
        return Result.success("这是网关测试: " + IdUtil.simpleUUID());
    }

    @GetMapping("/pay/gateway/filter")
    public Result<String> getFilter(HttpServletRequest request) {
        StringBuilder result = new StringBuilder();
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            String headerValue = request.getHeader(headerName);
            log.info("请求头: {}, 请求值: {}", headerName, headerValue);
            if (headerName.equalsIgnoreCase("X-Request-hao")) {
                result.append(headerName).append("\t").append(headerValue);
            }
        }
        System.out.println("===========================================");
        String customerId = request.getParameter("customerId");
        System.out.println("request parameter customerId : " +customerId);
        System.out.println("===========================================");
        System.out.println("===========================================");
        String customerName = request.getParameter("customerName");
        System.out.println("request parameter customerName : " +customerName);
        System.out.println("===========================================");
        return Result.success(result + "\t ID: " + IdUtil.simpleUUID());
    }
}
