package com.zhoujl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-23 10:38
 * @see: com.zhoujl
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.zhoujl.mapper")
public class TokenApplication {
    public static void main(String[] args) {
        SpringApplication.run(TokenApplication.class,args);
    }
}
