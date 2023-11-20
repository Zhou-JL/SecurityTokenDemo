package com.zhoujl.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-23 14:36
 * @see: com.zhoujl.utils
 * @Version: 1.0
 */
public class WebUtils
{
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
