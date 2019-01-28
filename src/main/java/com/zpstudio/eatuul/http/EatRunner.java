package com.zpstudio.eatuul.http;

import com.zpstudio.eatuul.filter.EatuulFilter;
import com.zpstudio.eatuul.filter.post.SendResponseFilter;
import com.zpstudio.eatuul.filter.pre.RequestWrapperFilter;
import com.zpstudio.eatuul.filter.route.RoutingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description TODO
 * @Author zhangpeng
 * @Date 2019/1/25 14:07
 **/
public class EatRunner {

    private ConcurrentHashMap<String, List<EatuulFilter>> hashFiltersByType = new ConcurrentHashMap<String, List<EatuulFilter>>(){
        {
            put("pre", new ArrayList<EatuulFilter>(){{add(new RequestWrapperFilter());}});
            put("route", new ArrayList<EatuulFilter>(){{add(new RoutingFilter());}});
            put("post", new ArrayList<EatuulFilter>(){{add(new SendResponseFilter());}});
        }
    };
    public void init(HttpServletRequest request, HttpServletResponse response) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setRequest(request);
        ctx.setResponse(response);
    }

    public void preRoute() {
        runFilters("pre");
    }

    public void route() {
        runFilters("route");
    }

    public void postRoute() {
        runFilters("post");
    }

    private void runFilters(String sType) {
        List<EatuulFilter> list = this.hashFiltersByType.get(sType);
        if (list != null) {
            for (EatuulFilter filter : list){
                filter.run();
            }
        }
    }
}
