package com.jdw.eureka.api.zuul.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 动态刷新配置类
 */
@RefreshScope
@Configuration
public class ConfigTest {
    /**
     * 从config-server中获取的配置项
     */
    @Value("${from}")
    private String from;

    public String getFrom() {
        return from;
    }
}
