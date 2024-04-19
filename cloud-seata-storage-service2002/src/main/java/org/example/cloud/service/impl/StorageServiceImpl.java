package org.example.cloud.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cloud.mapper.StorageMapper;
import org.example.cloud.service.StorageService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------------->StorageService 开始扣减库存");
        storageMapper.decrease(productId, count);
        log.info("------------->StorageService 扣减库存结束");
    }
}
