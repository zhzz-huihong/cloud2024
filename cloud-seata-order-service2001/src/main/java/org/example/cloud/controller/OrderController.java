package org.example.cloud.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cloud.entities.Order;
import org.example.cloud.response.Result;
import org.example.cloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public Result<Order> create(Order order) {
        orderService.create(order);
        return Result.success(order);
    }
}
