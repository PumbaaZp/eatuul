package com.zpstudio.eatuul.filter.route;

import com.zpstudio.eatuul.filter.EatuulFilter;
import com.zpstudio.eatuul.http.RequestContext;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO
 * @Author zhangpeng
 * @Date 2019/1/25 14:06
 **/
public class RoutingFilter extends EatuulFilter {
    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public void run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        RequestEntity requestEntity = ctx.getRequestEntity();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange(requestEntity, byte[].class);
        ctx.setResponseEntity(responseEntity);
    }
}
