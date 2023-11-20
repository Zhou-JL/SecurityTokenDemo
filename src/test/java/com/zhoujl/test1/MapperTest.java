package com.zhoujl.test1;

import com.zhoujl.domain.User;
import com.zhoujl.mapper.MenuMapper;
import com.zhoujl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-23 14:50
 * @see: com.zhoujl.test1
 * @Version: 1.0
 */
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }


    @Test
    public void testBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //对明文密码加密
        System.out.println(passwordEncoder.encode("123456"));   //$2a$10$ZTH5ihBvC9rQwSwTLZV61.drsdLq246eJ2/O/wOoYptdTUcAfyDfq
        //校验密码是否一直，明文密码，数据库密码
        System.out.println(passwordEncoder.matches("123456", "$2a$10$ZTH5ihBvC9rQwSwTLZV61.drsdLq246eJ2/O/wOoYptdTUcAfyDfq"));  //true

    }


    @Test
    public void testSelectPermsByUserId(){
        List<String> strings = menuMapper.selectPermsByUserId((long) 1);
        System.out.println(strings);
    }
}
