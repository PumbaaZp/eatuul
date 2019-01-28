package com.zpstudio.eatuul.filter;

/**
 * @Description TODO
 * @Author zhangpeng
 * @Date 2019/1/25 14:06
 **/
public abstract class EatuulFilter {

    abstract public String filterType();

    abstract public int filterOrder();

    abstract public void run();
}
