package com.liang.config;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class RibbonLoadBalancerAutoConfiguration {

    @RibbonLoadBalanced
    @Autowired(required = false)
    private List<RestTemplate> restTemplates = Collections.emptyList();

    @Bean
    public RibbonLoadBalancedInterceptor ribbonLoadBalancedInterceptor() {
        return new RibbonLoadBalancedInterceptor();
    }

    @Bean
    public SmartInitializingSingleton RibbonLoadBalancedRestTemplateInitializer() {
        return () -> {
            for (RestTemplate restTemplate : RibbonLoadBalancerAutoConfiguration.this.restTemplates) {
                List<ClientHttpRequestInterceptor> list = new ArrayList<>(restTemplate.getInterceptors());
                list.add(ribbonLoadBalancedInterceptor());
                restTemplate.setInterceptors(list);
            }
        };
    }
}