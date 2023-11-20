package com.zhoujl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhoujl.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-23 14:49
 * @see: com.zhoujl.mapper
 * @Version: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
