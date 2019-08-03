package com.eelve.springbootquartz.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.Map;

/**
 * 查询参数
 */
@Data
public class Query<T> {

    /**
     * mybatis-plus分页参数
     */
    private IPage<T> page;
    /**
     * 当前页码
     */
    private long currPage = 1;
    /**
     * 每页条数
     */
    private int limit = 10;

    public Query(Map<String, Object> params) {
        page = new Page<T>();
        // 当前页
        if (params.get("page") != null) {
            currPage = Long.parseLong((String) params.get("page"));

            page.setCurrent(currPage);
        }
        if (params.get("limit") != null) {
            // 每页大小
            limit = Integer.parseInt((String) params.get("limit"));
            page.setSize(limit);
        }

    }
}
