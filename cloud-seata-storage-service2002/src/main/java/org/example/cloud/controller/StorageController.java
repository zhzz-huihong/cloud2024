package org.example.cloud.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cloud.response.Result;
import org.example.cloud.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StorageController {
    @Resource
    private StorageService storageService;

    @PostMapping("/storage/decrease")
    Result<Object> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        storageService.decrease(productId, count);
        return Result.success("扣减库存成功");
    }
}
