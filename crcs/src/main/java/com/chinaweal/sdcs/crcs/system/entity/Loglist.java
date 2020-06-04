package com.chinaweal.sdcs.crcs.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-09-02
 */
public class Loglist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 操作人id
     */
    private String operationid;

    /**
     * 操作人名称
     */
    private String operationname;

    /**
     * 操作内容
     */
    private String operationinfo;

    /**
     * 操作类型
     */
    private String processid;

    /**
     * 业务主键
     */
    private String caseguid;

    /**
     * 操作时间
     */
    private Date operationtime;

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

    public String getOperationid() {
        return operationid;
    }

    public void setOperationid(String operationid) {
        this.operationid = operationid;
    }

    public String getOperationname() {
        return operationname;
    }

    public void setOperationname(String operationname) {
        this.operationname = operationname;
    }

    public String getOperationinfo() {
        return operationinfo;
    }

    public void setOperationinfo(String operationinfo) {
        this.operationinfo = operationinfo;
    }
    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid;
    }

    public String getCaseguid() {
        return caseguid;
    }

    public void setCaseguid(String caseguid) {
        this.caseguid = caseguid;
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "Loglist{" +
        "id=" + id +
        ", operationid=" + operationid +
        ", operationname=" + operationname +
        ", operationinfo=" + operationinfo +
        ", processid=" + processid +
        ", caseguid=" + caseguid +
        ", operationtime=" + operationtime +
        ", updatedate=" + updatedate +
        "}";
    }
}
