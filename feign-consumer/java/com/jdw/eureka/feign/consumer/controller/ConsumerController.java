package com.jdw.eureka.feign.consumer.controller;

import com.jdw.eureka.feign.consumer.dto.User;
import com.jdw.eureka.feign.consumer.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private HelloService helloService;

    @RequestMapping("/feignConsumer1.do")
    public String helloConsumer() {
        return helloService.hello();
    }

    @RequestMapping("/feignConsumer2.do")
    public String helloConsumer2() {
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello("zhangsan")).append("\n");
        sb.append(helloService.hello("lisi", 30)).append("\n");
        sb.append(helloService.hello(new User("wangwu", 40))).append("\n");
        return sb.toString();
    }

}
