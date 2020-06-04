package com.chinaweal.sdcs.crcs.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-10-29
 */
public class Dispatchbackinfo implements Serializable {

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
     * 操作id
     */
    private String nodeid;

    /**
     * 操作
     */
    private String nodename;

    /**
     * 处理人
     */
    private String handler;

    /**
     * 处理人id
     */
    private String handleruserid;

    /**
     * 处理部门
     */
    private String handledept;

    /**
     * 处理部门id
     */
    private String handledeptid;

    /**
     * 下一处理部门
     */
    private String nexthandledept;

    /**
     * 下一处理部门id
     */
    private String nexthandledeptid;

    /**
     * 处理时间
     */
    private Date handledate;

    /**
     * 更新时间
     */
    private Date updatedate;

    /**
     * 办理期限
     */
    private Date expiredate;

    /**
     * 初查反馈期限
     */
    private Date feedbackdate;

    /**
     * 附言
     */
    private String opinion;

    /**
     * 办结审批部门类型
     */
    private String handledepttype;

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
    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }
    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
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
    public String getHandledept() {
        return handledept;
    }

    public void setHandledept(String handledept) {
        this.handledept = handledept;
    }
    public String getHandledeptid() {
        return handledeptid;
    }

    public void setHandledeptid(String handledeptid) {
        this.handledeptid = handledeptid;
    }
    public String getNexthandledept() {
        return nexthandledept;
    }

    public void setNexthandledept(String nexthandledept) {
        this.nexthandledept = nexthandledept;
    }
    public String getNexthandledeptid() {
        return nexthandledeptid;
    }

    public void setNexthandledeptid(String nexthandledeptid) {
        this.nexthandledeptid = nexthandledeptid;
    }
    public Date getHandledate() {
        return handledate;
    }

    public void setHandledate(Date handledate) {
        this.handledate = handledate;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }
    public Date getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(Date feedbackdate) {
        this.feedbackdate = feedbackdate;
    }
    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
    public String getHandledepttype() {
        return handledepttype;
    }

    public void setHandledepttype(String handledepttype) {
        this.handledepttype = handledepttype;
    }

    @Override
    public String toString() {
        return "Dispatchbackinfo{" +
        "id=" + id +
        ", caseguid=" + caseguid +
        ", nodeid=" + nodeid +
        ", nodename=" + nodename +
        ", handler=" + handler +
        ", handleruserid=" + handleruserid +
        ", handledept=" + handledept +
        ", handledeptid=" + handledeptid +
        ", nexthandledept=" + nexthandledept +
        ", nexthandledeptid=" + nexthandledeptid +
        ", handledate=" + handledate +
        ", updatedate=" + updatedate +
        ", expiredate=" + expiredate +
        ", feedbackdate=" + feedbackdate +
        ", opinion=" + opinion +
        ", handledepttype=" + handledepttype +
        "}";
    }
}
