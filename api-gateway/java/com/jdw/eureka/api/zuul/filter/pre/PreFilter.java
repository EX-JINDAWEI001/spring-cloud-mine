package com.jdw.eureka.api.zuul.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        logger.info("start to myFilter......");
//        this.doSomeThing();
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
//            this.doSomeThing();
            HttpServletRequest request = ctx.getRequest();
            logger.info("send {} request to {}", request.getMethod(), request.getRequestURL());
            String token = request.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                logger.warn("access token is null !!!");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                return null;
            }
            return token;
        } catch (Exception e) {
            ctx.set("error.status_code", 666);
            ctx.set("error.exception", e);
            throw e;
        }
    }

    private void doSomeThing() {
        throw new RuntimeException("exists some error preFilter !!!");
    }

}
