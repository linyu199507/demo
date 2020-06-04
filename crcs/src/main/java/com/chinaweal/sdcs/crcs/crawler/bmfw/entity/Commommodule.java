package com.chinaweal.sdcs.crcs.crawler.bmfw.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-08-18
 * 通用表单
 */
public class Commommodule implements Serializable {

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
     * 表单类型
     */
    private String formtype;

    /**
     * 表单模板
     */
    private String formmodule;

    /**
     * 标题
     */
    private String rqsttitle;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 区域
     */
    private String region;

    /**
     * 是否有效
     */
    private String isvalid;

    /**
     * 来电号码
     */
    private String rqstnumber;

    /**
     * 联系电话
     */
    private String linknumber;

    /**
     * 反映类型
     */
    private String usertype;

    /**
     * 回复方式
     */
    private String answertype;

    /**
     * 企业类型
     */
    private String entitytype;

    /**
     * 企业全称
     */
    private String entname;

    /**
     * 企业登记机关
     */
    private String aicid;

    /**
     * 企业经营地址
     */
    private String address;

    /**
     * 工单类型
     */
    private String focus;

    /**
     * 工单类型2
     */
    private String focusxjfl;

    /**
     * 问题描述
     */
    private String rqstcontent;

    /**
     * 政务标签
     */
    private String govlabel;

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
    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }
    public String getFormmodule() {
        return formmodule;
    }

    public void setFormmodule(String formmodule) {
        this.formmodule = formmodule;
    }
    public String getRqsttitle() {
        return rqsttitle;
    }

    public void setRqsttitle(String rqsttitle) {
        this.rqsttitle = rqsttitle;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }
    public String getRqstnumber() {
        return rqstnumber;
    }

    public void setRqstnumber(String rqstnumber) {
        this.rqstnumber = rqstnumber;
    }
    public String getLinknumber() {
        return linknumber;
    }

    public void setLinknumber(String linknumber) {
        this.linknumber = linknumber;
    }
    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    public String getAnswertype() {
        return answertype;
    }

    public void setAnswertype(String answertype) {
        this.answertype = answertype;
    }
    public String getEntitytype() {
        return entitytype;
    }

    public void setEntitytype(String entitytype) {
        this.entitytype = entitytype;
    }
    public String getEntname() {
        return entname;
    }

    public void setEntname(String entname) {
        this.entname = entname;
    }
    public String getAicid() {
        return aicid;
    }

    public void setAicid(String aicid) {
        this.aicid = aicid;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }
    public String getFocusxjfl() {
        return focusxjfl;
    }

    public void setFocusxjfl(String focusxjfl) {
        this.focusxjfl = focusxjfl;
    }
    public String getRqstcontent() {
        return rqstcontent;
    }

    public void setRqstcontent(String rqstcontent) {
        this.rqstcontent = rqstcontent;
    }
    public String getGovlabel() {
        return govlabel;
    }

    public void setGovlabel(String govlabel) {
        this.govlabel = govlabel;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "Commommodule{" +
        "id=" + id +
        ", caseguid=" + caseguid +
        ", formtype=" + formtype +
        ", formmodule=" + formmodule +
        ", rqsttitle=" + rqsttitle +
        ", name=" + name +
        ", gender=" + gender +
        ", region=" + region +
        ", isvalid=" + isvalid +
        ", rqstnumber=" + rqstnumber +
        ", linknumber=" + linknumber +
        ", usertype=" + usertype +
        ", answertype=" + answertype +
        ", entitytype=" + entitytype +
        ", entname=" + entname +
        ", aicid=" + aicid +
        ", address=" + address +
        ", focus=" + focus +
        ", focusxjfl=" + focusxjfl +
        ", rqstcontent=" + rqstcontent +
        ", govlabel=" + govlabel +
        ", updatedate=" + updatedate +
        "}";
    }
}
