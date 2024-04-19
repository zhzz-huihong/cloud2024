package org.example.cloud.controller;

import jakarta.annotation.Resource;
import org.example.cloud.entities.PayDTO;
import org.example.cloud.response.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

//    public static final String PaymentService_URL = "http://localhost:8001"; 硬编码
    public static final String PaymentService_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/pay/add")
    public Result addOrder(@RequestBody PayDTO payDTO){
        return restTemplate.postForObject(PaymentService_URL + "/pay/add", payDTO, Result.class);
    }

    //注意！！！此处特殊
    @DeleteMapping("/consumer/pay/del/{id}")
    public Result delOrder(@PathVariable("id") Integer id){
        return restTemplate.exchange(PaymentService_URL + "/pay/del/" + id, HttpMethod.DELETE,null, Result.class).getBody();
    }
    //注意！！！此处特殊
    @PutMapping("/consumer/pay/update")
    public Result updateOrder(@RequestBody PayDTO payDTO){
        return restTemplate.exchange(PaymentService_URL +"/pay/update",HttpMethod.PUT,new HttpEntity<>(payDTO), Result.class).getBody();
    }

    @GetMapping("/consumer/pay/get/{id}")
    public Result getOrderById(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentService_URL+"/pay/get/"+id, Result.class,id);
    }

    @GetMapping("/consumer/pay/getAll")
    public Result getAllOrder(){
        return restTemplate.getForObject(PaymentService_URL+"/pay/getAll", Result.class);
    }

    @GetMapping("/consumer/pay/getInfo")
    public Result getInfo(){
        return restTemplate.getForObject(PaymentService_URL+"/pay/getInfo", Result.class);
    }
}
