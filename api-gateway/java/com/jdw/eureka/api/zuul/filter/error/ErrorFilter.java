package com.jdw.eureka.api.zuul.filter.error;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

/**
 * 1、如果异常没有被try-catch包围，那就必须通过实现error类型过滤器，在run（）方法中给请求上下文设置error.status_code等属性，
 * 2、如果代码已被try-catch包围，记得在catch块中给请求上下文设置error.status_code等属性，
 * 3、不管哪种设置方式，必须有一处设置了error.status_code等属性，才能使SendErrorFilter正确识别异常信息并将信息返回前端
 */
@Component
public class ErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        ctx.set("error.status_code", 999);
        ctx.set("error.exception", throwable.getCause());
        return null;
    }

}
