package com.zpstudio.eatuul.filter.pre;

import com.zpstudio.eatuul.filter.EatuulFilter;
import com.zpstudio.eatuul.http.RequestContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Description TODO
 * @Author zhangpeng
 * @Date 2019/1/25 14:06
 **/
public class RequestWrapperFilter extends EatuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public void run() {
        String rootUrl = "http://localhost:9090";
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest servletRequest = ctx.getRequest();
        String targetUrl = rootUrl.concat(servletRequest.getRequestURI());
        RequestEntity<byte[]> requestEntity = null;
        try{
            requestEntity = createRequestEntity(servletRequest, targetUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        ctx.setRequestEntity(requestEntity);
    }

    private RequestEntity<byte[]> createRequestEntity(HttpServletRequest servletRequest, String url) throws URISyntaxException, IOException {
        String method = servletRequest.getMethod();
        HttpMethod httpMethod = HttpMethod.resolve(method);
        MultiValueMap<String, String> headers = createRequestHeaders(servletRequest);
        byte[] body = createRequestBody(servletRequest);
        return new RequestEntity<>(body, headers, httpMethod, new URI(url));
    }

    private byte[] createRequestBody(HttpServletRequest request) throws IOException {
        return StreamUtils.copyToByteArray(request.getInputStream());
    }

    private MultiValueMap<String, String> createRequestHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            for (String headerValue : headerValues) {
                headers.add(headerName, headerValue);
            }
        }
        return headers;
    }
}
