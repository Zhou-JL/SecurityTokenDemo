package com.zhoujl.expression;

import com.zhoujl.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-27 10:51
 * @see: com.zhoujl.expression
 * @Version: 1.0
 *
 * 自定义权限校验方法
 */
@Component("ex")
public class MyExpression {


    /**
     * authority代表自己传入的权限
     * @param authority
     * @return
     */
    public boolean hasAuthority(String authority){
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        //判断用户权限集合中是否存在authority，authority代表自己传入的权限
        return permissions.contains(authority);
    }
}
