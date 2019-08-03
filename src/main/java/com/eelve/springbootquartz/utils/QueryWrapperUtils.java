package com.eelve.springbootquartz.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;

/**
 * EntityWrapper的封装，用于查询使用
 */
public class QueryWrapperUtils {

    /**
     * 模糊查询封装
     *
     * @param ew         必须传入
     * @param searchKey  多条件之间使用空格隔开
     * @param columNames 实体对应的属性
     * @return
     */
    public static <T> QueryWrapper wrapperLike(QueryWrapper<T> ew, String searchKey, String... columNames) {
        if (columNames == null || columNames.length == 0) {
            return ew;
        }
        /**
         * 按照属性模糊查询
         */
        if (StringUtils.isNotBlank(searchKey)) {
            /**
             * 多个条件之间使用空格隔开
             */
            String[] searchKeyArr = searchKey.split(" ");
            for (String key : searchKeyArr) {
                if (StringUtils.isBlank(key)) {
                    continue;
                }
                ew.and(wrapper -> {
                    for (int i = 0; i < columNames.length; i++) {
                        String column = columNames[i];
                        wrapper.or().like(StringUtils.isNotBlank(key), column, key);
                    }
                    return wrapper;
                });
            }
        }
        return ew;
    }
}
