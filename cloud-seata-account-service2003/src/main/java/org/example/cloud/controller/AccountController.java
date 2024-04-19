package org.example.cloud.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cloud.response.Result;
import org.example.cloud.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/account/decrease")
    Result<Object> decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money) {
        accountService.decrease(userId, money);
        return Result.success("扣减余额成功");
    }
}
