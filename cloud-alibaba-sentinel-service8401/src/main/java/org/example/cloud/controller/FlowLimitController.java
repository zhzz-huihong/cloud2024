package org.example.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cloud.service.FlowLimitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
        return "into ... A ...";
    }

    @GetMapping("/testB")
    public String testB() {
        return "into ... B ...";
    }

    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testC")
    public String testC() {
        flowLimitService.common();
        return "into ... C ...";
    }

    @GetMapping("/testD")
    public String testD() {
        flowLimitService.common();
        return "into ... D ...";
    }

    @GetMapping("/testE")
    public String testE() {
        flowLimitService.common();
        log.info(System.currentTimeMillis() + "                 流控效果");
        return "into ... E ...";
    }

    @GetMapping("/testF")
    public String testF() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        flowLimitService.common();
        log.info(System.currentTimeMillis() + "                 流控效果");
        return "into ... F ...";
    }

    @GetMapping("/testG")
    public String testG() {
        int a = 1 / 0;
        return "into ... G ...";
    }

    @GetMapping("/testH/{id}")
    @SentinelResource(value = "testH", blockHandler = "testHBlock", fallback = "testHFallback")
    public String testH(@PathVariable("id") Integer id) {
        if (id == 0) {
            throw new RuntimeException("RuntimeException");
        }
        return "into ... H ...";
    }

    public String testHBlock(@PathVariable("id") Integer id, BlockException blockException) {
        System.out.println("testHBlock  限流");
        return "testHBlock  限流";
    }

    public String testHFallback(@PathVariable("id") Integer id, Throwable throwable) {
        System.out.println("testHBlock 降级");
        return "testHBlock 降级";
    }


    @GetMapping("/testI")
    @SentinelResource(value = "testI", blockHandler = "testHHotKey")
    public String testH(@RequestParam("id") Integer id, @RequestParam("id2") Integer id2) {
        return "into ... I ...";
    }

    public String testHHotKey(@RequestParam("id") Integer id, @RequestParam("id2") Integer id2, BlockException blockException) {
        return "testHBlock  限流 热点";
    }
}
