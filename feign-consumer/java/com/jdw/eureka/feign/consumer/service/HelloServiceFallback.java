package com.jdw.eureka.feign.consumer.service;

import com.jdw.eureka.feign.consumer.dto.User;
import org.springframework.stereotype.Component;

/**
 * 服务降级实现类
 */
@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "hello error !!!";
    }

    @Override
    public String hello(String name) {
        return "hello " + name + " error !!!" ;
    }

    @Override
    public User hello(String name, Integer age) {
        return new User("error", 999);
    }

    @Override
    public String hello(User user) {
        return "hello " + user + " error !!!";
    }
}
