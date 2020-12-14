package com.wuxinhua.model;

import java.util.List;

public class RestPageBean {

    /**
     * 总数
     */
    private Long total;

    /**
     * 分页数据
     */
    private List<?> data;


    public RestPageBean() {
    }

    public RestPageBean(Long total, List<?> data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
