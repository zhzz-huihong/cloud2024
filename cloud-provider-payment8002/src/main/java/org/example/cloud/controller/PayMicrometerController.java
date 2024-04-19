package org.example.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.example.cloud.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayMicrometerController {
    @GetMapping("/pay/micrometer/{id}")
    public Result<String> myMicrometer(@PathVariable("id") Integer id) {
        return Result.success("这是链路追踪, Id:" + IdUtil.simpleUUID());
    }
}
