package com.zhoujl.config;

import com.zhoujl.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-23 15:45
 * @see: com.zhoujl.config
 * @Version: 1.0
 *
 * 启动时加载
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)   //开启使用注解配置权限
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /**
     * 使用BCryptPasswordEncoder 的方式对密码进行加密校验，使用PasswordEncoder进行密码校验
     *  * 会自动将我们输入的明文密码进行  passwordEncoder.matches("输入的明文", "数据库密文")
     *
     *  @Test
     *     public void testBCryptPasswordEncoder(){
     *         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
     *         //对明文密码加密
     *         System.out.println(passwordEncoder.encode("123456"));   //$2a$10$ZTH5ihBvC9rQwSwTLZV61.drsdLq246eJ2/O/wOoYptdTUcAfyDfq
     *         //校验密码是否一直，明文密码，数据库密码
     *         System.out.println(passwordEncoder.matches("123456", "$2a$10$ZTH5ihBvC9rQwSwTLZV61.drsdLq246eJ2/O/wOoYptdTUcAfyDfq"));  //true
     *     }
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                //基于配置的权限控制
                .antMatchers("/test").hasAuthority("system:dept:list")    //test接口具有"system:dept:list"才可以访问
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        //配置过滤器
        //把token校验过滤器添加到过滤器链中,把这个jwtAuthenticationTokenFilter过滤器放到UsernamePasswordAuthenticationFilter过滤器之前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        //配置异常处理
        //配置异常处理器，处理security认证和授权时抛出的异常，把原有的异常转化为我们自定义的异常处理
        http.exceptionHandling()
                //认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                //授权失败处理器
                .accessDeniedHandler(accessDeniedHandler);

        //允许跨域
        http.cors();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
