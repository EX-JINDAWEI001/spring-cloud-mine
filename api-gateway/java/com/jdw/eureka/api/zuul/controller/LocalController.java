package com.jdw.eureka.api.zuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本地跳转接口
 */
@RequestMapping("/local")
@RestController
public class LocalController {

    @Autowired
    private ConfigTest configTest;

    @RequestMapping("/hello.do")
    public String hello() {
        return "Hello World Local ~ " + configTest.getFrom();
    }

}
