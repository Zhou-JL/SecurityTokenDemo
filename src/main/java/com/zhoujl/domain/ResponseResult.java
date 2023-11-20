package com.zhoujl.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Author: zjl
 * @company: 北京汉唐智创科技有限公司
 * @time: 2023-2-23 11:09
 * @see: com.zhoujl.domain
 * @Version: 1.0
 *
 * 统一响应类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 查询到的结果数据
     */
    private T data;

    public ResponseResult(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code,T data){
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
