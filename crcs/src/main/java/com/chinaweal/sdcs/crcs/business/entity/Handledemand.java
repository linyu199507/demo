package com.chinaweal.sdcs.crcs.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-08-20
 */
public class Handledemand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务主键
     */
    private String id;

    /**
     * 业务主键
     */
    private String caseguid;

    /**
     * 初步意见应反馈时限1
     */
    private Date opiniontimeshould;

    /**
     * 初步意见应反馈时限2
     */
    private Date opiniontimesurplus;

    /**
     * 初步意见反馈时间
     */
    private Date opiniontime;

    /**
     * 应受理时限1
     */
    private Date accepttimeshould;

    /**
     * 应受理时限2
     */
    private Date accepttimesurplus;

    /**
     * 受理时间
     */
    private Date accepttime;

    /**
     * 应立案时限1
     */
    private Date caseputtimeshould;

    /**
     * 应立案时限2
     */
    private Date caseputtimesurplus;

    /**
     * 立案时间
     */
    private Date caseputtime;

    /**
     * 应反馈时限1
     */
    private Date resulttimeshould;

    /**
     * 应反馈时限2
     */
    private Date resulttimesurplus;

    /**
     * 反馈时间
     */
    private Date resulttime;

    /**
     * 初步意见剩余时间
     */
    private String opiniontimeminstr;

    /**
     * 应受理时限剩余时间
     */
    private String accepttimeminstr;

    /**
     * 应立案时限剩余时间
     */
    private String caseputtimeminstr;

    /**
     * 应反馈时限剩余时间
     */
    private String resulttimeminstr;

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
    public Date getOpiniontimeshould() {
        return opiniontimeshould;
    }

    public void setOpiniontimeshould(Date opiniontimeshould) {
        this.opiniontimeshould = opiniontimeshould;
    }
    public Date getOpiniontimesurplus() {
        return opiniontimesurplus;
    }

    public void setOpiniontimesurplus(Date opiniontimesurplus) {
        this.opiniontimesurplus = opiniontimesurplus;
    }
    public Date getOpiniontime() {
        return opiniontime;
    }

    public void setOpiniontime(Date opiniontime) {
        this.opiniontime = opiniontime;
    }
    public Date getAccepttimeshould() {
        return accepttimeshould;
    }

    public void setAccepttimeshould(Date accepttimeshould) {
        this.accepttimeshould = accepttimeshould;
    }
    public Date getAccepttimesurplus() {
        return accepttimesurplus;
    }

    public void setAccepttimesurplus(Date accepttimesurplus) {
        this.accepttimesurplus = accepttimesurplus;
    }
    public Date getAccepttime() {
        return accepttime;
    }

    public void setAccepttime(Date accepttime) {
        this.accepttime = accepttime;
    }
    public Date getCaseputtimeshould() {
        return caseputtimeshould;
    }

    public void setCaseputtimeshould(Date caseputtimeshould) {
        this.caseputtimeshould = caseputtimeshould;
    }
    public Date getCaseputtimesurplus() {
        return caseputtimesurplus;
    }

    public void setCaseputtimesurplus(Date caseputtimesurplus) {
        this.caseputtimesurplus = caseputtimesurplus;
    }
    public Date getCaseputtime() {
        return caseputtime;
    }

    public void setCaseputtime(Date caseputtime) {
        this.caseputtime = caseputtime;
    }
    public Date getResulttimeshould() {
        return resulttimeshould;
    }

    public void setResulttimeshould(Date resulttimeshould) {
        this.resulttimeshould = resulttimeshould;
    }
    public Date getResulttimesurplus() {
        return resulttimesurplus;
    }

    public void setResulttimesurplus(Date resulttimesurplus) {
        this.resulttimesurplus = resulttimesurplus;
    }
    public Date getResulttime() {
        return resulttime;
    }

    public void setResulttime(Date resulttime) {
        this.resulttime = resulttime;
    }
    public String getOpiniontimeminstr() {
        return opiniontimeminstr;
    }

    public void setOpiniontimeminstr(String opiniontimeminstr) {
        this.opiniontimeminstr = opiniontimeminstr;
    }
    public String getAccepttimeminstr() {
        return accepttimeminstr;
    }

    public void setAccepttimeminstr(String accepttimeminstr) {
        this.accepttimeminstr = accepttimeminstr;
    }
    public String getCaseputtimeminstr() {
        return caseputtimeminstr;
    }

    public void setCaseputtimeminstr(String caseputtimeminstr) {
        this.caseputtimeminstr = caseputtimeminstr;
    }
    public String getResulttimeminstr() {
        return resulttimeminstr;
    }

    public void setResulttimeminstr(String resulttimeminstr) {
        this.resulttimeminstr = resulttimeminstr;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "Inrequest{" +
        "id=" + id +
        ", caseguid=" + caseguid +
        ", opiniontimeshould=" + opiniontimeshould +
        ", opiniontimesurplus=" + opiniontimesurplus +
        ", opiniontime=" + opiniontime +
        ", accepttimeshould=" + accepttimeshould +
        ", accepttimesurplus=" + accepttimesurplus +
        ", accepttime=" + accepttime +
        ", caseputtimeshould=" + caseputtimeshould +
        ", caseputtimesurplus=" + caseputtimesurplus +
        ", caseputtime=" + caseputtime +
        ", resulttimeshould=" + resulttimeshould +
        ", resulttimesurplus=" + resulttimesurplus +
        ", resulttime=" + resulttime +
        ", opiniontimeminstr=" + opiniontimeminstr +
        ", accepttimeminstr=" + accepttimeminstr +
        ", caseputtimeminstr=" + caseputtimeminstr +
        ", resulttimeminstr=" + resulttimeminstr +
        ", updatedate=" + updatedate +
        "}";
    }
}
