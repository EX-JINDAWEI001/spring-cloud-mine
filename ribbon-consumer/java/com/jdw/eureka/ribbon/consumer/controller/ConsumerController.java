package com.jdw.eureka.ribbon.consumer.controller;

import com.jdw.eureka.ribbon.consumer.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ConsumerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate template;

    @Autowired
    private HelloService helloService;

    @RequestMapping("/ribbonConsumer1.do")
    public String helloConsumer1(HttpServletRequest request) {
        return template.getForEntity("http://HELLO-SERVICE/hello.do", String.class).getBody();
    }

    @RequestMapping("/ribbonConsumer2.do")
    public String helloConsumer2() {
        return helloService.helloService();
    }

}
