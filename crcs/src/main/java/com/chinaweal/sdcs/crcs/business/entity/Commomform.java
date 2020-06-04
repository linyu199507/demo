package com.chinaweal.sdcs.crcs.business.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 * 通用表单信息
 */
public class Commomform implements Serializable {

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
     * 工单号
     */
    @Excel(name = "工单号")
    private String tserialnum;

    /**
     * 表单类型
     */
    @Excel(name = "表单类型")
    private String formtype;

    /**
     * 表单模板
     */
    @Excel(name = "表单模板")
    private String formmodule;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String rqsttitle;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String gender;

    /**
     * 区域
     */
    @Excel(name = "区域")
    private String region;

    /**
     * 是否有效
     */
    @Excel(name = "是否有效")
    private String isvalid;

    /**
     * 来电号码
     */
    @Excel(name = "来电号码")
    private String rqstphonenumber;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String linkphonenumber;

    /**
     * 反映类型
     */
    @Excel(name = "反映类型")
    private String usertype;

    /**
     * 回复方式
     */
    @Excel(name = "回复方式")
    private String answertype;

    /**
     * 企业类型
     */
    @Excel(name = "企业类型")
    private String entitytype;

    /**
     * 企业类型codeid
     */
    @Excel(name = "企业类型codeid")
    private String entitytypecodeid;


    /**
     * 企业全称
     */
    @Excel(name = "企业全称")
    private String entname;

    /**
     * 企业全称--新
     */
    @Excel(name = "企业全称")
    private String subjectname;

    /**
     * 企业登记机关
     */
    @Excel(name = "企业登记机关")
    private String aicid;

    /**
     * 企业经营地址
     */
    @Excel(name = "企业经营地址")
    private String address;

    /**
     * 工单类型
     */
    @Excel(name = "工单类型")
    private String focus;

    /**
     * 工单类型2
     */
    @Excel(name = "")
    private String focusxjfl;

    /**
     * 问题描述
     */
    @Excel(name = "问题描述")
    private String rqstcontent;

    /**
     * 政务标签
     */
    @Excel(name = "政务标签")
    private String govlabel;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间")
    private Date updatedate;

    /**
     * 业务分类codeid
     */
    @Excel(name = "业务分类codeid")
    private String businesstypecodeid;

    /**
     * 业务分类
     */
    @Excel(name = "业务分类")
    private String businesstype;

    /**
     * 关键字
     */
    @Excel(name = "关键字")
    private String keyword;

    /**
     * 途径
     */
    @Excel(name = "途径")
    private String appealsource;

    /**
     * 移送编号
     */
    @Excel(name = "移送编号")
    private String transfernumber;

    /**
     * 来函单位
     */
    @Excel(name = "来函单位")
    private String incomingcommpany;

    /**
     * 是否职投
     */
    @Excel(name = "是否职投")
    private String isprofessional;

    /**
     * 咨询方地址
     */
    @Excel(name = "咨询方地址")
    private String consultantaddress;

    /**
     * 处理单位
     */
    @Excel(name = "处理单位")
    private String processingunit;

    /**
     * 咨询方地址
     */
    @Excel(name = "案件接收日期")
    private Date acceptdate;

    /**
     * 涉及问题分类--中文
     */
    private String complaintstype;

    /**
     * 涉及问题分类--codeid
     */
    private String complaintstypecodeid;

    /**
     * 登记机构(信访)
     */
    private String registration;

    /**
     * 承办机构(信访)
     */
    private String undertake;

    /**
     * 满意度(信访)
     */
    private String satisfied;

    /**
     * 最终反馈日期
     */
    private Date finalfeedbackdate;



    /**
     * 办理跟踪表
     */
    @Excel(name = "办理跟踪表")
    //隐藏,不用再mapper.xml文件和数据表中添加相应字段
    @TableField(exist = false)
    private List<Map<String, Opinion>> opinion;

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

    public String getTserialnum() {
        return tserialnum;
    }

    public void setTserialnum(String tserialnum) {
        this.tserialnum = tserialnum;
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

    public String getRqstphonenumber() {
        return rqstphonenumber;
    }

    public void setRqstphonenumber(String rqstphonenumber) {
        this.rqstphonenumber = rqstphonenumber;
    }

    public String getLinkphonenumber() {
        return linkphonenumber;
    }

    public void setLinkphonenumber(String linkphonenumber) {
        this.linkphonenumber = linkphonenumber;
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

    public String getEntitytypecodeid() {
        return entitytypecodeid;
    }

    public void setEntitytypecodeid(String entitytypecodeid) {
        this.entitytypecodeid = entitytypecodeid;
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

    public String getBusinesstypecodeid() {
        return businesstypecodeid;
    }

    public void setBusinesstypecodeid(String businesstypecodeid) {
        this.businesstypecodeid = businesstypecodeid;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAppealsource() {
        return appealsource;
    }

    public void setAppealsource(String appealsource) {
        this.appealsource = appealsource;
    }

    public String getTransfernumber() {
        return transfernumber;
    }

    public void setTransfernumber(String transfernumber) {
        this.transfernumber = transfernumber;
    }

    public String getIncomingcommpany() {
        return incomingcommpany;
    }

    public void setIncomingcommpany(String incomingcommpany) {
        this.incomingcommpany = incomingcommpany;
    }

    public String getIsprofessional() {
        return isprofessional;
    }

    public void setIsprofessional(String isprofessional) {
        this.isprofessional = isprofessional;
    }

    public String getConsultantaddress() {
        return consultantaddress;
    }

    public void setConsultantaddress(String consultantaddress) {
        this.consultantaddress = consultantaddress;
    }

    public String getProcessingunit() {
        return processingunit;
    }

    public void setProcessingunit(String processingunit) {
        this.processingunit = processingunit;
    }

    public Date getAcceptdate() {
        return acceptdate;
    }

    public void setAcceptdate(Date acceptdate) {
        this.acceptdate = acceptdate;
    }

    public List<Map<String, Opinion>> getOpinion() {
        return opinion;
    }

    public void setOpinion(List<Map<String, Opinion>> opinion) {
        this.opinion = opinion;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getComplaintstype() {
        return complaintstype;
    }

    public void setComplaintstype(String complaintstype) {
        this.complaintstype = complaintstype;
    }

    public String getComplaintstypecodeid() {
        return complaintstypecodeid;
    }

    public void setComplaintstypecodeid(String complaintstypecodeid) {
        this.complaintstypecodeid = complaintstypecodeid;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getUndertake() {
        return undertake;
    }

    public void setUndertake(String undertake) {
        this.undertake = undertake;
    }

    public String getSatisfied() {
        return satisfied;
    }

    public void setSatisfied(String satisfied) {
        this.satisfied = satisfied;
    }

    public Date getFinalfeedbackdate() {
        return finalfeedbackdate;
    }

    public void setFinalfeedbackdate(Date finalfeedbackdate) {
        this.finalfeedbackdate = finalfeedbackdate;
    }


    @Override
    public String toString() {
        return "Commomform{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", tserialnum='" + tserialnum + '\'' +
                ", formtype='" + formtype + '\'' +
                ", formmodule='" + formmodule + '\'' +
                ", rqsttitle='" + rqsttitle + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", region='" + region + '\'' +
                ", isvalid='" + isvalid + '\'' +
                ", rqstphonenumber='" + rqstphonenumber + '\'' +
                ", linkphonenumber='" + linkphonenumber + '\'' +
                ", usertype='" + usertype + '\'' +
                ", answertype='" + answertype + '\'' +
                ", entitytype='" + entitytype + '\'' +
                ", entitytypecodeid='" + entitytypecodeid + '\'' +
                ", entname='" + entname + '\'' +
                ", subjectname='" + subjectname + '\'' +
                ", aicid='" + aicid + '\'' +
                ", address='" + address + '\'' +
                ", focus='" + focus + '\'' +
                ", focusxjfl='" + focusxjfl + '\'' +
                ", rqstcontent='" + rqstcontent + '\'' +
                ", govlabel='" + govlabel + '\'' +
                ", updatedate=" + updatedate +
                ", businesstypecodeid='" + businesstypecodeid + '\'' +
                ", businesstype='" + businesstype + '\'' +
                ", keyword='" + keyword + '\'' +
                ", appealsource='" + appealsource + '\'' +
                ", transfernumber='" + transfernumber + '\'' +
                ", incomingcommpany='" + incomingcommpany + '\'' +
                ", isprofessional='" + isprofessional + '\'' +
                ", consultantaddress='" + consultantaddress + '\'' +
                ", processingunit='" + processingunit + '\'' +
                ", acceptdate=" + acceptdate +
                ", complaintstype='" + complaintstype + '\'' +
                ", complaintstypecodeid='" + complaintstypecodeid + '\'' +
                ", registration='" + registration + '\'' +
                ", undertake='" + undertake + '\'' +
                ", satisfied='" + satisfied + '\'' +
                ", finalfeedbackdate='" + finalfeedbackdate + '\'' +
                ", opinion=" + opinion +
                '}';
    }
}
