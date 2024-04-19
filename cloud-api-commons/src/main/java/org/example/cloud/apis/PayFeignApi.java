package org.example.cloud.apis;

import org.example.cloud.entities.PayDTO;
import org.example.cloud.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "cloud-gateway")
//@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {

    @PostMapping(value = "/pay/add")
    public Result addPay(@RequestBody PayDTO payDTO);

    @DeleteMapping(value = "/pay/del/{id}")
    public Result deletePay(@PathVariable("id") Integer id);

    @PutMapping(value = "/pay/update")
    public Result updatePay(@RequestBody PayDTO payDTO);

    @GetMapping(value = "/pay/get/{id}")
    public Result getById(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/getAll")
    public Result getAll();

    @GetMapping("/pay/getInfo")
    public Result getInfo();

    @GetMapping("/pay/circuit/{id}")
    Result<String> myCircuit(@PathVariable("id") Integer id);

    @GetMapping("/pay/bulkhead/{id}")
    Result<String> myBulkhead(@PathVariable("id") Integer id);

    @GetMapping("/pay/rateLimit/{id}")
    Result<String> myRateLimit(@PathVariable("id") Integer id);

    @GetMapping("/pay/micrometer/{id}")
    Result<String> myMicrometer(@PathVariable("id") Integer id);

    @GetMapping("/pay/gateway/get/{id}")
    Result<PayDTO> getById4Gateway(@PathVariable("id") Integer id);

    @GetMapping("/pay/gateway/getInfo")
    Result<String> getInfo4Gateway();

}
