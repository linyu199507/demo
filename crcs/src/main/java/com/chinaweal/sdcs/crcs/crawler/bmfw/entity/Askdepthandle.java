package com.chinaweal.sdcs.crcs.crawler.bmfw.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-08-18
 * 处理流程表
 */
public class Askdepthandle implements Serializable {

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
     * 表单号
     */
    private String serialnum;

    /**
     * 步骤
     */
    private String activitytype;

    /**
     * 处理部门
     */
    private String operatorouname;

    /**
     * 处理人
     */
    private String operateusername;

    /**
     * 操作
     */
    private String opeartionstatus;

    /**
     * 处理时间
     */
    private Date handletime;

    /**
     * 处理意见
     */
    private String handleopinion;

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
    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }
    public String getActivitytype() {
        return activitytype;
    }

    public void setActivitytype(String activitytype) {
        this.activitytype = activitytype;
    }
    public String getOperatorouname() {
        return operatorouname;
    }

    public void setOperatorouname(String operatorouname) {
        this.operatorouname = operatorouname;
    }
    public String getOperateusername() {
        return operateusername;
    }

    public void setOperateusername(String operateusername) {
        this.operateusername = operateusername;
    }
    public String getOpeartionstatus() {
        return opeartionstatus;
    }

    public void setOpeartionstatus(String opeartionstatus) {
        this.opeartionstatus = opeartionstatus;
    }
    public Date getHandletime() {
        return handletime;
    }

    public void setHandletime(Date handletime) {
        this.handletime = handletime;
    }
    public String getHandleopinion() {
        return handleopinion;
    }

    public void setHandleopinion(String handleopinion) {
        this.handleopinion = handleopinion;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "Askdepthandle{" +
        "id=" + id +
        ", caseguid=" + caseguid +
        ", serialnum=" + serialnum +
        ", activitytype=" + activitytype +
        ", operatorouname=" + operatorouname +
        ", operateusername=" + operateusername +
        ", opeartionstatus=" + opeartionstatus +
        ", handletime=" + handletime +
        ", handleopinion=" + handleopinion +
        ", updatedate=" + updatedate +
        "}";
    }
}
