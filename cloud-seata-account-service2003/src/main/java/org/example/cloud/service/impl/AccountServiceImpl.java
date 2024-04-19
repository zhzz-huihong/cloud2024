package org.example.cloud.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cloud.mapper.AccountMapper;
import org.example.cloud.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, Long money) {
        log.info("------------->AccountService 开始扣减余额");
        accountMapper.decrease(userId, money);
        log.info("------------->AccountService 开始扣减余额");


        // 超时异常
//         timeout();
        // 抛出异常
//         int i = 10 / 0;
    }

    private void timeout() {
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
