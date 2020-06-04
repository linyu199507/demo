package com.chinaweal.sdcs.crcs.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-08-18
 * 材料文书表
 */
public class Materialinfo implements Serializable {

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
     * 文书类型
     */
    private String materialtype;

    /**
     * 文书数量
     */
    private BigDecimal amount;

    /**
     * 文书路径
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
    private String attachfilename;
    /**
     * 文件回填状态；source=原来系统爬取的文件、localBack=本地文件而且已经进行回填了、local=本地文件，等待回填
     */
    private String backfillState;

    public Materialinfo(String id, String caseguid, String materialtype, BigDecimal amount, String filesavepath,
                        String status, Date uploadtime, Date updatedate, String attachfilename) {
        this.id = id;
        this.caseguid = caseguid;
        this.materialtype = materialtype;
        this.amount = amount;
        this.filesavepath = filesavepath;
        this.status = status;
        this.uploadtime = uploadtime;
        this.updatedate = updatedate;
        this.attachfilename = attachfilename;
    }

    public Materialinfo() {
    }

    public String getBackfillState() {
        return backfillState;
    }

    public void setBackfillState(String backfillState) {
        this.backfillState = backfillState;
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

    public String getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(String materialtype) {
        this.materialtype = materialtype;
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

    public String getAttachfilename() {
        return attachfilename;
    }

    public void setAttachfilename(String attachfilename) {
        this.attachfilename = attachfilename;
    }

    @Override
    public String toString() {
        return "Materialinfo{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", materialtype='" + materialtype + '\'' +
                ", amount=" + amount +
                ", filesavepath='" + filesavepath + '\'' +
                ", status='" + status + '\'' +
                ", uploadtime=" + uploadtime +
                ", updatedate=" + updatedate +
                ", attachfilename='" + attachfilename + '\'' +
                ", backfillState='" + backfillState + '\'' +
                '}';
    }
}
