package com.eelve.springbootquartz.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * 定义Json响应数据
 *
 * @param <T>
 */
@Data
public class JsonResultUtil<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    /**
     * 成功
     *
     * @return
     */
    public static JsonResultUtil ok() {
        JsonResultUtil result = new JsonResultUtil();
        result.setCode(0);
        return result;
    }

    /**
     * 失败
     *
     * @param msg
     * @return
     */
    public static JsonResultUtil error(String msg) {
        JsonResultUtil result = new JsonResultUtil();
        result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     * @return
     */
    public static JsonResultUtil error(Integer code, String msg) {
        JsonResultUtil result = new JsonResultUtil();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败
     *
     * @return
     */
    public static JsonResultUtil error() {
        JsonResultUtil result = new JsonResultUtil();
        result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        result.setMsg("未知异常，请联系管理员");
        return result;
    }

    /**
     * 添加返回的数据
     *
     * @param data
     * @return
     */
    public JsonResultUtil<T> put(T data) {
        this.data = data;
        return this;
    }

    /**
     * 是否正常
     *
     * @return
     */
    @JsonIgnore
    public boolean isOk() {
        return this.code.intValue() == 0;
    }
    @JsonIgnore
    public boolean isError() {
        return this.code.intValue() != 0;
    }
}
