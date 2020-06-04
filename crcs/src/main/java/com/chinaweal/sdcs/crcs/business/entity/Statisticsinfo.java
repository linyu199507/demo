package com.chinaweal.sdcs.crcs.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-11-19
 * 历史统计数据文件表
 */
public class Statisticsinfo  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 业务主键
     */
    private String caseguid;

    /**
     * 文件类型
     */
    private String filetype;

    /**
     * 文件数量
     */
    private BigDecimal amount;

    /**
     * 文件路径
     */
    private String filesavepath;

    /**
     * 是否有效
     */
    private String status;

    /**
     * 上传时间
     */
    private Date uploadtime;

    /**
     * 更新时间
     */
    private Date updatedate;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 处理人
     */
    private String handler;

    /**
     * 处理人id
     */
    private String handleruserid;

    public Statisticsinfo(String id, String caseguid, String filetype, BigDecimal amount, String filesavepath,
                          String status, Date uploadtime, Date updatedate, String filename,String handler,String handleruserid) {
        this.id = id;
        this.caseguid = caseguid;
        this.filetype = filetype;
        this.amount = amount;
        this.filesavepath = filesavepath;
        this.status = status;
        this.uploadtime = uploadtime;
        this.updatedate = updatedate;
        this.filename = filename;
        this.handler = handler;
        this.handleruserid = handleruserid;
    }

    public Statisticsinfo() {
    }

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

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFilesavepath() {
        return filesavepath;
    }

    public void setFilesavepath(String filesavepath) {
        this.filesavepath = filesavepath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getHandleruserid() {
        return handleruserid;
    }

    public void setHandleruserid(String handleruserid) {
        this.handleruserid = handleruserid;
    }

    @Override
    public String toString() {
        return "Statisticsinfo{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", filetype='" + filetype + '\'' +
                ", amount=" + amount +
                ", filesavepath='" + filesavepath + '\'' +
                ", status='" + status + '\'' +
                ", uploadtime=" + uploadtime +
                ", updatedate=" + updatedate +
                ", filename='" + filename + '\'' +
                ", handler='" + handler + '\'' +
                ", handleruserid='" + handleruserid + '\'' +
                '}';
    }
}
