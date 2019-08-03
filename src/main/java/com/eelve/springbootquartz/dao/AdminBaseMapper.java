package com.eelve.springbootquartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 全局的mapper
 * 这里可以设置通用的方法
 * 如逻辑删除
 *
 * @author liuchengbiao
 * @date 2019/4/20 下午4:02
 */
public interface AdminBaseMapper<T> extends BaseMapper<T> {
    /**
     * 根据ID删除
     * 自动更新del_token字段为主键值
     *
     * @param entity
     * @return
     */
    int removeByIdWithFillDelToken(T entity);

    /**
     * 批量删除
     * 自动更新del_token字段为主键值
     *
     * @param list
     */
    default void removeByIdsWithFillDelToken(List<T> list) {
        for (T t : list) {
            removeByIdWithFillDelToken(t);
        }
    }
}
