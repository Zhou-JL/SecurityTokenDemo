package com.zhoujl.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-23 15:00
 * @see: com.zhoujl.domain
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    //存储权限信息，这个权限信息是给我们看的，因为继承了UserDetails，所以UserDetails所需要的权限信息是从下面的getAuthorities获取的
    private List<String> permissions;


    public LoginUser(User user,List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }


    //存储SpringSecurity所需要的权限信息的集合
    @JSONField(serialize = false)   //不会序列化到redis中
    private List<GrantedAuthority> authorities;

    @Override
    public  Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities!=null){
            return authorities;
        }
        //相当于for循环
        //把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities中
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
