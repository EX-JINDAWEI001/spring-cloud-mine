package com.jdw.eureka.ribbon.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    @Autowired
    private RestTemplate template;

    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String helloService() {
        return template.getForEntity("http://HELLO-SERVICE/hello.do", String.class).getBody();
    }

    public String helloFallBack() {
        return "system is busy now !";
    }

}


