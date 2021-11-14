package com.jdw.eureka.api.zuul.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本地跳转接口
 */
@RequestMapping("/local")
@RestController
public class LocalController {

    /**
     * 从config-server中获取的配置项
     */
    @Value("${from}")
    private String from;

    @RequestMapping("/hello.do")
    public String hello() {
        return "Hello World Local ~" + from;
    }

}
