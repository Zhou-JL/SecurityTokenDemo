package com.zhoujl.service;

import com.zhoujl.domain.ResponseResult;
import com.zhoujl.domain.User;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-23 16:13
 * @see: com.zhoujl.service
 * @Version: 1.0
 */
public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
