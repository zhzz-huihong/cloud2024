package org.example.cloud.service.impl;

import jakarta.annotation.Resource;
import org.example.cloud.entities.Pay;
import org.example.cloud.mapper.PayMapper;
import org.example.cloud.service.PayService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PayServiceImpl  implements PayService {
    @Resource
    private PayMapper payMapper;
    @Override
    public int add(Pay pay) {
        return payMapper.insertSelective(pay);
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
