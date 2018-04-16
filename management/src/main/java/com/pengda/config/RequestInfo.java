package com.pengda.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by pd on 2017/3/7.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInfo<T> {
    private T query;

    /**
     * 第几页
     */
    private Integer currentPage;

    /**
     * 每页显示的条数
     */
    private Integer pageSize;
}
