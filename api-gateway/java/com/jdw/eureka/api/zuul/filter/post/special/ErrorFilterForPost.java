package com.jdw.eureka.api.zuul.filter.post.special;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

/**
 * 专门用来处理post过滤器抛出的异常：
 *
 * 通过继承SendErrorFilter，以便使用它的run（）方法来统一处理异常信息。
 * 重写SendErrorFilter的filterType，定义为error过滤器
 * 重写SendErrorFilter的filterOrder，使它大于ErrorFilter的order
 * 重写SendErrorFilter的shouldFilter，只针对post过滤器抛出的错误
 *
 */
@Component
public class ErrorFilterForPost extends SendErrorFilter {
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
