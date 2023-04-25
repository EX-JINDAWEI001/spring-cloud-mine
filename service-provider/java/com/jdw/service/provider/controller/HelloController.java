package com.jdw.service.provider.controller;

import com.jdw.service.provider.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello.do")
    public String hello() {
        int sleepTime = new Random().nextInt(3000);
        logger.info("hello sleepTime is {}", sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            logger.error("thread is interrupted!!!", e);
        }
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello.do, host:{}, service_id:{}, port:{}", instance.getHost(), instance.getServiceId(), instance.getPort());
        return "Hello World";
    }

    @RequestMapping("/hello1.do")
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

    @RequestMapping("/hello2.do")
    public User hello(@RequestHeader String name, @RequestHeader Integer age) {
        return new User(name, age);
    }

    @RequestMapping("/hello3.do")
    public String hello(@RequestBody User user) {
        return user.toString();
    }

}
