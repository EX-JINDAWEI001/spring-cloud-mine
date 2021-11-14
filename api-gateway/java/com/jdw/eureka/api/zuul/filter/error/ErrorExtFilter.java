package com.jdw.eureka.api.zuul.filter.error;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

/**
 * 根据com.netflix.zuul.http.ZuulServlet的service方法处理步骤可知
 * post阶段抛出的异常，由error过滤器处理之后，并不会再调用post阶段的过滤器（SendErrorFilter）
 * 这会导致post过滤器抛出异常后，异常信息无法正常返回前端，前端显示一片空白
 *
 * 通过继承SendErrorFilter，以便使用它的run（）方法来统一处理异常信息。
 * 重写SendErrorFilter的filterType，定义为error过滤器
 * 重写SendErrorFilter的filterOrder，使它大于ErrorFilter的order
 * 重写SendErrorFilter的shouldFilter，只针对post过滤器抛出的错误
 *
 */
@Component
public class ErrorExtFilter extends SendErrorFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 11;
    }

    @Override
    public boolean shouldFilter() {
        //根据MyFilterProcessor异常处理中设置的failed.filter属性判断，仅处理来自post过滤器引起的异常
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulFilter failedFilter = (ZuulFilter) ctx.get("failed.filter");
        if (failedFilter != null && failedFilter.filterType().equals("post")) {
            return true;
        }
        return false;
    }

}
