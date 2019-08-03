package com.eelve.springbootquartz.validator;


import com.eelve.springbootquartz.exception.EIPException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 */
public abstract class Assert {

    /**
     * 如果为空抛出异常
     *
     * @param str
     * @param message
     */
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new EIPException(message);
        }
    }

    /**
     * 如果为空抛出异常
     *
     * @param object
     * @param message
     */
    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new EIPException(message);
        }
    }
}
