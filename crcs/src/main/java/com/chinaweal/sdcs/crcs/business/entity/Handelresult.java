package com.chinaweal.sdcs.crcs.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-08-18
 * 处理结果表
 */
public class Handelresult implements Serializable {

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
     * 处理结果
     */
    private String finalresult;

    /**
     * 处理人
     */
    private String handler;

    /**
     * 处理人id
     */
    private String handleruserid;

    /**
     * 处理时间
     */
    private Date handletime;

    /**
     * 是否反馈
     */
    private String isfeedback;

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
    public String getFinalresult() {
        return finalresult;
    }

    public void setFinalresult(String finalresult) {
        this.finalresult = finalresult;
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
    public Date getHandletime() {
        return handletime;
    }

    public void setHandletime(Date handletime) {
        this.handletime = handletime;
    }
    public String getIsfeedback() {
        return isfeedback;
    }

    public void setIsfeedback(String isfeedback) {
        this.isfeedback = isfeedback;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "Handelresult{" +
        "id=" + id +
        ", caseguid=" + caseguid +
        ", finalresult=" + finalresult +
        ", handler=" + handler +
        ", handleruserid=" + handleruserid +
        ", handletime=" + handletime +
        ", isfeedback=" + isfeedback +
        ", updatedate=" + updatedate +
        "}";
    }
}
