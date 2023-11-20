package com.zhoujl.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhoujl.domain.LoginUser;
import com.zhoujl.domain.User;
import com.zhoujl.mapper.MenuMapper;
import com.zhoujl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;   //注意这个不需要自己定义，是来自于这个包下的
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-23 14:58
 * @see: com.zhoujl.service.serviceImpl
 * @Version: 1.0
 * 用户认证的时候走这里
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("走这里");
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
//        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
        System.out.println("pp");
        //封装成UserDetails对象返回,这里是通过有参构造注入的
        return new LoginUser(user,list);
    }
}
