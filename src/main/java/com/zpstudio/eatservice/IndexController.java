package com.zpstudio.eatservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author zhangpeng
 * @Date 2019/1/28 14:34
 **/

@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index access success";
    }
}