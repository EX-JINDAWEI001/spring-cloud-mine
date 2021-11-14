package com.jdw.eureka.api.zuul.filter.post;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

@Component
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 30;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        this.doSomeThing();
        return null;
    }

    private void doSomeThing() {
        throw new RuntimeException("exists some error postFilter !!!");
    }

}
