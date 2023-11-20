package com.zhoujl.controller;

import com.zhoujl.service.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zhoujl.domain.ResponseResult;
import com.zhoujl.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-23 16:11
 * @see: com.zhoujl.controller
 * @Version: 1.0
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }


    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
