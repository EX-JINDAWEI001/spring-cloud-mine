package com.jdw.eureka.api.zuul.filter.post.special;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 专门用来处理post过滤器抛出的异常：
 *
 * 根据com.netflix.zuul.http.ZuulServlet的service方法处理步骤可知:
 * post阶段抛出的异常，由error过滤器处理之后，并不会再调用post阶段的过滤器（SendErrorFilter），
 * 这会导致post过滤器抛出异常后，异常信息无法正常返回前端，前端显示一片空白;
 *
 * FilterProcessor类是zuul中的核心过滤器，所有过滤器的执行都在它里面调用，例如：
 * preRoute(): 按filterOrder顺序执行所有pre类型过滤器;
 * route(): 按filterOrder顺序执行所有route类型过滤器;
 * error(): 按filterOrder顺序执行所有error类型过滤器;
 * postRoute()：按filterOrder顺序执行所有post类型过滤器;
 *
 * 通过继承FilterProcessor并重写processZuulFilter方法，
 * 主逻辑不变，只在异常时设置failed.filter上下文属性，以便给ErrorFilterForPost判断异常来源用,
 * 需要在应用主类中调用FilterProcessor.setProcessor(new MyFilterProcessor());
 */
public class MyFilterProcessor extends FilterProcessor {
    @Override
    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
        try {
            return super.processZuulFilter(filter);
        } catch (ZuulException e) {
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.set("failed.filter", filter);
            throw e;
        }
    }
}
