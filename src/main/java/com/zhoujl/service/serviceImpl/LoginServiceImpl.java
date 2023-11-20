package com.zhoujl.service.serviceImpl;

import com.zhoujl.domain.LoginUser;
import com.zhoujl.domain.ResponseResult;
import com.zhoujl.domain.User;
import com.zhoujl.service.LoginService;
import com.zhoujl.utils.JwtUtil;
import com.zhoujl.utils.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-23 16:13
 * @see: com.zhoujl.service.serviceImpl
 * @Version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        System.out.println("b");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //用户认证,这里走了UserDetailsServiceImpl中的方法
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证失败
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        System.out.println("c");
        //认证通过，使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return new ResponseResult(200,"登陆成功",map);
    }


    //获取SecurityContextHolder中的认证信息，删除redis中对应的数据即可
    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"退出成功");
    }
}
