package com.chinaweal.sdcs.crcs.crawler.bmfw.entity;

import java.io.Serializable;
import java.util.Date;

public class Accessory  implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 业务id
     */
    private String caseguid;
    /**
     * 文件名
     */
    private String attachfilename;
    /**
     * 文件id
     */
    private String attachguid;
    /**
     * 文件大小
     */
    private String attachlength;
    /**
     * 文件地址
     */
    private String downloadurl;
    /**
     * 请求方法
     */
    private String posturl;
    /**
     * 是否只读
     */
    private String readonly;
    /**
     * 本地保存地址
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

    public String getCaseguid() {
        return caseguid;
    }

    public void setCaseguid(String caseguid) {
        this.caseguid = caseguid;
    }

    public String getAttachfilename() {
        return attachfilename;
    }

    public void setAttachfilename(String attachfilename) {
        this.attachfilename = attachfilename;
    }

    public String getAttachguid() {
        return attachguid;
    }

    public void setAttachguid(String attachguid) {
        this.attachguid = attachguid;
    }

    public String getAttachlength() {
        return attachlength;
    }

    public void setAttachlength(String attachlength) {
        this.attachlength = attachlength;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public String getPosturl() {
        return posturl;
    }

    public void setPosturl(String posturl) {
        this.posturl = posturl;
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
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
        return "Accessory{" +
                "id=" + id +
                ", caseguid=" + caseguid +
                ", attachfilename=" + attachfilename +
                ", attachguid=" + attachguid +
                ", attachlength=" + attachlength +
                ", downloadurl=" + downloadurl +
                ", posturl=" + posturl +
                ", readonly=" + readonly +
                ", localaddress=" + localaddress +
                ", updatedate=" + updatedate +
                "}";
    }
}
