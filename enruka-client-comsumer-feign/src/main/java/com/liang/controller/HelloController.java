package com.liang.controller;

import com.liang.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liang
 * com.liang.controller HelloController  2021/1/3 0003  Liang
 */
@RestController
public class HelloController {

    @Autowired
    private FeignService mFeignService;

    @GetMapping("/callHello")
    public String hello() {
        return mFeignService.hello();
    }
}
