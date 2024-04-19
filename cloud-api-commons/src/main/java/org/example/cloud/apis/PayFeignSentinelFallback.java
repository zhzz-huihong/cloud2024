package org.example.cloud.apis;

import org.example.cloud.entities.PayDTO;
import org.example.cloud.response.Result;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelFallback implements PayFeignSentinelApi{
    @Override
    public Result<PayDTO> getPayByOrderNo(String orderNo) {
        return Result.fail("服务不可达");
    }
}
