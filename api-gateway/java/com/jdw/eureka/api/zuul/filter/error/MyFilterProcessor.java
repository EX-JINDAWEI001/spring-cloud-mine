package com.jdw.eureka.api.zuul.filter.error;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 继承FilterProcessor重写processZuulFilter方法，
 * 主逻辑不变，只在异常时设置failed.filter上下文属性，
 * 以便给ErrorExtFilter判断异常来源用
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
