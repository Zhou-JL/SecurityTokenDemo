package com.zhoujl.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-22 16:46
 * @see: com.zhoujl.controller
 * @Version: 1.0
 */
@RestController
public class HelloController {


    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('system:dept:list')")  //所需权限
    @PreAuthorize("hasAnyAuthority('system:dept:list','admin','test')")  //只要具有权限中的任意一个就可以访问
    public String hello(){
        return "hello";
    }



    @RequestMapping("/hello1")
    @PreAuthorize("@ex.hasAuthority('system:dept:list')")   //使用 自定义权限校验方法
    public String hello1(){
        return "hello1";
    }


    @RequestMapping("/test")   //权限配置在配置文件中
    public String test(){
        return "test";
    }


}
