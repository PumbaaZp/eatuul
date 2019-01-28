package com.zpstudio.eatuul.http;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description TODO
 * @Author zhangpeng
 * @Date 2019/1/25 14:07
 **/
@WebServlet(name = "eatuul", urlPatterns = "/*")
public class EatuulServlet extends HttpServlet {

    private EatRunner eatRunner = new EatRunner();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将request与response放入上下文
        eatRunner.init(request, response);
        try{
            //前置过滤
            eatRunner.preRoute();
            //过滤
            eatRunner.route();
            //后置过滤
            eatRunner.postRoute();
        }catch (Throwable e){
            RequestContext.getCurrentContext().getResponse().sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }finally {
            //清楚变量
            RequestContext.getCurrentContext().unset();
        }
    }

}
