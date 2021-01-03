package com.liang.service;

import com.liang.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-client-provider-service", configuration = FeignConfig.class)
public interface FeignService {

    @GetMapping("/hello")
    String hello();
}