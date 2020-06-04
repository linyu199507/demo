package com.chinaweal.sdcs.crcs.crawler.bmfw.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-08-18
 * 待办列表
 */
public class Datagriddata implements Serializable {

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
     * 工单分类
     */
    private String focus;

    /**
     * 紧急状态
     */
    private String teti;

    /**
     * 表单号
     */
    private String serialnum;

    /**
     * 预留表单号
     */
    private String contractnum;

    /**
     * 工单号
     */
    private String tserialnum;

    /**
     * 工单标题
     */
    private String rqsttitle;

    /**
     * 业务分类
     */
    private String accordtype;

    /**
     * 政府标签
     */
    private String labelname;

    /**
     * 交办时间
     */
    private Date createdate;

    /**
     * 剩余时间
     */
    private String resulttimemin;

    /**
     * 请求头地址
     */
    private String handleurl;

    /**
     * 是否已处理
     */
    private String status;

    /**
     * 是否有效（0有效 1失效）
     */
    private String state;

    /**
     * 是否已处理
     */
    private String formtype;

    /**
     * 更新时间
     */
    private Date updatedate;

    /**
     * 是否重派
     */
    private String iscp;

    public String getIscp() {
        return iscp;
    }

    public void setIscp(String iscp) {
        this.iscp = iscp;
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

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getTeti() {
        return teti;
    }

    public void setTeti(String teti) {
        this.teti = teti;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getContractnum() {
        return contractnum;
    }

    public void setContractnum(String contractnum) {
        this.contractnum = contractnum;
    }

    public String getTserialnum() {
        return tserialnum;
    }

    public void setTserialnum(String tserialnum) {
        this.tserialnum = tserialnum;
    }

    public String getRqsttitle() {
        return rqsttitle;
    }

    public void setRqsttitle(String rqsttitle) {
        this.rqsttitle = rqsttitle;
    }

    public String getAccordtype() {
        return accordtype;
    }

    public void setAccordtype(String accordtype) {
        this.accordtype = accordtype;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getResulttimemin() {
        return resulttimemin;
    }

    public void setResulttimemin(String resulttimemin) {
        this.resulttimemin = resulttimemin;
    }

    public String getHandleurl() {
        return handleurl;
    }

    public void setHandleurl(String handleurl) {
        this.handleurl = handleurl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    @Override
    public String toString() {
        return "Datagriddata{" +
                "id=" + id +
                ", caseguid=" + caseguid +
                ", focus=" + focus +
                ", teti=" + teti +
                ", serialnum=" + serialnum +
                ", contractnum=" + contractnum +
                ", tserialnum=" + tserialnum +
                ", rqsttitle=" + rqsttitle +
                ", accordtype=" + accordtype +
                ", labelname=" + labelname +
                ", createdate=" + createdate +
                ", resulttimemin=" + resulttimemin +
                ", handleurl=" + handleurl +
                ", status=" + status +
                ", state=" + state +
                ", formtype=" + formtype +
                ", updatedate=" + updatedate +
                ", iscp=" + iscp +
                "}";
    }
}
