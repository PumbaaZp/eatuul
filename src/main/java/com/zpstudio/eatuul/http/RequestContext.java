package com.zpstudio.eatuul.http;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description TODO
 * @Author zhangpeng
 * @Date 2019/1/25 14:07
 **/
public class RequestContext extends ConcurrentHashMap<String, Object> {
    protected static Class<? extends RequestContext> contextClass = RequestContext.class;
    protected static final ThreadLocal<? extends RequestContext> threadLocal = ThreadLocal.withInitial(()->{
        try{
            return contextClass.newInstance();
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
    });

    public static RequestContext getCurrentContext(){
        return threadLocal.get();
    }

    public HttpServletRequest getRequest(){
        return (HttpServletRequest) get("request");
    }
    public void setRequest(HttpServletRequest request){
        put("request", request);
    }
    public HttpServletResponse getResponse(){
        return (HttpServletResponse) get("response");
    }
    public void setResponse(HttpServletResponse response){
        set("response", response);
    }
    public RequestEntity getRequestEntity(){
        return (RequestEntity) get("requestEntity");
    }
    public void setRequestEntity(RequestEntity entity) {
        set("requestEntity", entity);
    }
    public ResponseEntity getResponseEntity() {
        return (ResponseEntity) get("responseEntity");
    }
    public void setResponseEntity(ResponseEntity entity){
        set("responseEntity", entity);
    }

    public void set(String key, Object value) {
        if(value != null)
            put(key, value);
        else
            remove(key);
    }

    public void unset(){
        threadLocal.remove();
    }

}
