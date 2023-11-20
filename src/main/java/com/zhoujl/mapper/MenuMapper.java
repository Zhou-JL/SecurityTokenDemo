package com.zhoujl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhoujl.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-24 15:32
 * @see: com.zhoujl.mapper
 * @Version: 1.0
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long userid);
}
