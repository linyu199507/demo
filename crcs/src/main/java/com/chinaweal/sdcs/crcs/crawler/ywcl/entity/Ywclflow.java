package com.chinaweal.sdcs.crcs.crawler.ywcl.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-11-11
 */
public class Ywclflow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 业务主键
     */
    private String caseid;

    /**
     * 本级id
     */
    private String keyid;

    /**
     * 父级id
     */
    private String parentid;

    /**
     * 操作人
     */
    private String fldjr;

    /**
     * 操作人姓名
     */
    private String fldjrname;

    /**
     * 操作时间
     */
    private Date zcsj;

    /**
     * 操作
     */
    private String fllxname;

    /**
     * 咨询单位名称
     */
    private String zxunitname;

    /**
     * 咨询部门名称
     */
    private String zxbmname;

    /**
     * 接收单位名称
     */
    private String jsunitname;

    /**
     * 接收部门名称
     */
    private String jsbmname;

    /**
     * 附言
     */
    private String zcfy;

    /**
     * 完整内容
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

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }
    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }
    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
    public String getFldjr() {
        return fldjr;
    }

    public void setFldjr(String fldjr) {
        this.fldjr = fldjr;
    }
    public String getFldjrname() {
        return fldjrname;
    }

    public void setFldjrname(String fldjrname) {
        this.fldjrname = fldjrname;
    }
    public Date getZcsj() {
        return zcsj;
    }

    public void setZcsj(Date zcsj) {
        this.zcsj = zcsj;
    }
    public String getFllxname() {
        return fllxname;
    }

    public void setFllxname(String fllxname) {
        this.fllxname = fllxname;
    }
    public String getZxunitname() {
        return zxunitname;
    }

    public void setZxunitname(String zxunitname) {
        this.zxunitname = zxunitname;
    }
    public String getZxbmname() {
        return zxbmname;
    }

    public void setZxbmname(String zxbmname) {
        this.zxbmname = zxbmname;
    }
    public String getJsunitname() {
        return jsunitname;
    }

    public void setJsunitname(String jsunitname) {
        this.jsunitname = jsunitname;
    }
    public String getJsbmname() {
        return jsbmname;
    }

    public void setJsbmname(String jsbmname) {
        this.jsbmname = jsbmname;
    }
    public String getZcfy() {
        return zcfy;
    }

    public void setZcfy(String zcfy) {
        this.zcfy = zcfy;
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
        return "Ywclflow{" +
        "id=" + id +
        ", caseid=" + caseid +
        ", caseid=" + caseid +
        ", keyid=" + keyid +
        ", parentid=" + parentid +
        ", fldjr=" + fldjr +
        ", fldjrname=" + fldjrname +
        ", zcsj=" + zcsj +
        ", fllxname=" + fllxname +
        ", zxunitname=" + zxunitname +
        ", zxbmname=" + zxbmname +
        ", jsunitname=" + jsunitname +
        ", jsbmname=" + jsbmname +
        ", zcfy=" + zcfy +
        ", record=" + record +
        ", updatedate=" + updatedate +
        "}";
    }
}
