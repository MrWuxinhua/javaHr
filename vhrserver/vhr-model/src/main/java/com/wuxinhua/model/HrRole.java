package com.wuxinhua.model;

public class HrRole {
    private Integer id;

    private Integer hrid;

    private Integer rid;

    public HrRole(Integer id, Integer hrid, Integer rid) {
        this.id = id;
        this.hrid = hrid;
        this.rid = rid;
    }

    public HrRole() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHrid() {
        return hrid;
    }

    public void setHrid(Integer hrid) {
        this.hrid = hrid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}