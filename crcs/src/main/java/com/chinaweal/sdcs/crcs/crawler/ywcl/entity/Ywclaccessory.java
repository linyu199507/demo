package com.chinaweal.sdcs.crcs.crawler.ywcl.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-11-07
 */
public class Ywclaccessory implements Serializable {

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
     * 请求参数（id）
     */
    private String fileid;

    /**
     * 文件类型（fjlx）
     */
    private String biztype;

    /**
     * 文件名称
     */
    private String fname;

    /**
     * 上传时间
     */
    private Date timestr;

    /**
     * 上传人
     */
    private String ownername;

    /**
     * 文件大小
     */
    private String sizestr;

    /**
     * 请求文件的下载地址
     */
    private String posturl;

    /**
     * 本地保存路径
     */
    private String localaddress;

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
    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }
    public String getBiztype() {
        return biztype;
    }

    public void setBiztype(String biztype) {
        this.biztype = biztype;
    }
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    public Date getTimestr() {
        return timestr;
    }

    public void setTimestr(Date timestr) {
        this.timestr = timestr;
    }
    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }
    public String getSizestr() {
        return sizestr;
    }

    public void setSizestr(String sizestr) {
        this.sizestr = sizestr;
    }
    public String getPosturl() {
        return posturl;
    }

    public void setPosturl(String posturl) {
        this.posturl = posturl;
    }
    public String getLocaladdress() {
        return localaddress;
    }

    public void setLocaladdress(String localaddress) {
        this.localaddress = localaddress;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "Ywclaccessory{" +
        "id=" + id +
        ", caseid=" + caseid +
        ", fileid=" + fileid +
        ", biztype=" + biztype +
        ", fname=" + fname +
        ", timestr=" + timestr +
        ", ownername=" + ownername +
        ", sizestr=" + sizestr +
        ", posturl=" + posturl +
        ", localaddress=" + localaddress +
        ", updatedate=" + updatedate +
        "}";
    }
}
