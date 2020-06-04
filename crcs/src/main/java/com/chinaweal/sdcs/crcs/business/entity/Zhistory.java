package com.chinaweal.sdcs.crcs.business.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2020-03-17
 */
public class Zhistory implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private String id;

    /**
     * 回填json
     */
    private String zjson;

    /**
     * 表单类型
     */
    private String ztype;

    /**
     * 业务id
     */
    private String caseid;

    /**
     * 调用成功否状态
     */
    private String record;

    /**
     * 更新时间
     */
    private Date updatedate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZjson() {
        return zjson;
    }

    public void setZjson(String zjson) {
        this.zjson = zjson;
    }
    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }
    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }
    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "Zhistory{" +
        "zjson=" + zjson +
        ", ztype=" + ztype +
        ", caseid=" + caseid +
        ", record=" + record +
        ", updatedate=" + updatedate +
        "}";
    }
}
