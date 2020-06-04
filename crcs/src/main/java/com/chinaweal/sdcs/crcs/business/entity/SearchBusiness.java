package com.chinaweal.sdcs.crcs.business.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.util.Date;

/**
     * 业务记录查询结果列表
     *
     * @author ：Lin Yu
     * @version : 1.0
     * @since ： 2019/9/2
     */
    @ExcelTarget(value = "SearchBusiness")
    public class SearchBusiness implements Serializable {
        private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @Excel(name = "序号", orderNum = "0")
        private Integer index;

    /**
     * 工单分类
     */
    @Excel(name = "工单分类", orderNum = "1")
    private String focus;

    /**
     * 工单号
     */
    @Excel(name = "工单号", orderNum = "2")
    private String tserialnum;

    /**
     * 工单标题
     */
    @Excel(name = "工单标题", orderNum = "3")
    private String rqsttitle;

    /**
     * 业务分类
     */
    @Excel(name = "业务分类", orderNum = "4")
    private String accordtype;

    /**
     * 交办时间
     */
    @Excel(name = "交办时间", orderNum = "5")
    private String assigndate;

    /**
     * 系统来源
     */
    @Excel(name = "系统来源", orderNum = "6")
    private String systemsource;


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
     * 政府标签
     */
    private String labelname;



    /**
     * 到期时间
     */
    private Date expiredate;

    /**
     * 剩余时间
     */
    private String resulttimemin;


    /**
     * 是否签收
     */
    private String issignin;

    /**
     * 签收人账号
     */
    private String signinaccount;

    /**
     * 签收人名称
     */
    private String signinname;

    /**
     * 签收人主键
     */
    private String signinuserid;

    /**
     * 签收人所属部门
     */
    private String signindeptid;

    /**
     * 签收时间
     */
    private Date signdate;

    /**
     * 表单类型
     */
    private String formtype;

    /**
     * 操作节点
     */
    private String operate;

    /**
     * 是否有效（1有效 0失效）
     */
    private String state;

    /**
     * 更新时间
     */
    private Date updatedate;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
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

    public String getAssigndate() {
        return assigndate;
    }

    public void setAssigndate(String assigndate) {
        this.assigndate = assigndate;
    }

    public String getSystemsource() {
        return systemsource;
    }

    public void setSystemsource(String systemsource) {
        this.systemsource = systemsource;
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

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    public String getResulttimemin() {
        return resulttimemin;
    }

    public void setResulttimemin(String resulttimemin) {
        this.resulttimemin = resulttimemin;
    }

    public String getIssignin() {
        return issignin;
    }

    public void setIssignin(String issignin) {
        this.issignin = issignin;
    }

    public String getSigninaccount() {
        return signinaccount;
    }

    public void setSigninaccount(String signinaccount) {
        this.signinaccount = signinaccount;
    }

    public String getSigninname() {
        return signinname;
    }

    public void setSigninname(String signinname) {
        this.signinname = signinname;
    }

    public String getSigninuserid() {
        return signinuserid;
    }

    public void setSigninuserid(String signinuserid) {
        this.signinuserid = signinuserid;
    }

    public String getSignindeptid() {
        return signindeptid;
    }

    public void setSignindeptid(String signindeptid) {
        this.signindeptid = signindeptid;
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
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

    @Override
    public String toString() {
        return "SearchBusiness{" +
                "index=" + index +
                ", focus='" + focus + '\'' +
                ", tserialnum='" + tserialnum + '\'' +
                ", rqsttitle='" + rqsttitle + '\'' +
                ", accordtype='" + accordtype + '\'' +
                ", assigndate=" + assigndate +
                ", systemsource='" + systemsource + '\'' +
                ", id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", serialnum='" + serialnum + '\'' +
                ", labelname='" + labelname + '\'' +
                ", expiredate=" + expiredate +
                ", resulttimemin='" + resulttimemin + '\'' +
                ", issignin='" + issignin + '\'' +
                ", signinaccount='" + signinaccount + '\'' +
                ", signinname='" + signinname + '\'' +
                ", signinuserid='" + signinuserid + '\'' +
                ", signindeptid='" + signindeptid + '\'' +
                ", signdate=" + signdate +
                ", formtype='" + formtype + '\'' +
                ", operate='" + operate + '\'' +
                ", state='" + state + '\'' +
                ", updatedate=" + updatedate +
                '}';
    }
}

