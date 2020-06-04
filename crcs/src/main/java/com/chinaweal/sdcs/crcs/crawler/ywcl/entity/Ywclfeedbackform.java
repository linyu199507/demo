package com.chinaweal.sdcs.crcs.crawler.ywcl.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-11-11
 */
public class Ywclfeedbackform implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 系统业务主键
     */
    private String caseguid;

    /**
     * 12315业务主键
     */
    private String caseid;

    /**
     * 反馈时间
     */
    private Date feedbacktime;

    /**
     * 操作人员
     */
    private String operationstaff;

    /**
     * 进行业务
     */
    private String business;

    /**
     * 操作选择
     */
    private String operationchoice;

    /**
     * 内容
     */
    private String content;

    /**
     * 完整记录
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

    public String getCaseguid() {
        return caseguid;
    }

    public void setCaseguid(String caseguid) {
        this.caseguid = caseguid;
    }
    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }
    public Date getFeedbacktime() {
        return feedbacktime;
    }

    public void setFeedbacktime(Date feedbacktime) {
        this.feedbacktime = feedbacktime;
    }
    public String getOperationstaff() {
        return operationstaff;
    }

    public void setOperationstaff(String operationstaff) {
        this.operationstaff = operationstaff;
    }
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
    public String getOperationchoice() {
        return operationchoice;
    }

    public void setOperationchoice(String operationchoice) {
        this.operationchoice = operationchoice;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Ywclfeedbackform{" +
        "id=" + id +
        ", caseguid=" + caseguid +
        ", caseid=" + caseid +
        ", feedbacktime=" + feedbacktime +
        ", operationstaff=" + operationstaff +
        ", business=" + business +
        ", operationchoice=" + operationchoice +
        ", content=" + content +
        ", record=" + record +
        ", updatedate=" + updatedate +
        "}";
    }
}
