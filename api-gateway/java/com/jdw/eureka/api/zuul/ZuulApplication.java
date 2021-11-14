package com.jdw.eureka.api.zuul;

import com.jdw.eureka.api.zuul.filter.error.MyFilterProcessor;
import com.netflix.zuul.FilterProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
        FilterProcessor.setProcessor(new MyFilterProcessor());
    }

}


