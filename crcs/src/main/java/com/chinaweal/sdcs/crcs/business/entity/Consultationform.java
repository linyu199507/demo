package com.chinaweal.sdcs.crcs.business.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-22
 */
public class Consultationform implements Serializable {

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
     * 来电号码
     */
    @Excel(name = "来电号码")
    private String rqstnumber;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String linknumber;

    /**
     * 信息公开
     */
    @Excel(name = "信息公开")
    private String informationopen;

    /**
     * 举报人地址
     */
    @Excel(name = "举报人地址")
    private String address;

    /**
     * 反馈时间
     */
    @Excel(name = "")
    private String usertype;

    /**
     * 工单分类
     */
    @Excel(name = "工单分类")
    private String focus;

    /**
     * 反映类型
     */
    @Excel(name = "反映类型")
    private String reflectiontype;


    /**
     * 类别
     */
    @Excel(name = "类别")
    private String category;


    /**
     * 诉求来源/途径
     */
    @Excel(name = "诉求来源/途径")
    private String appealsource;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String rqsttitle;

    /**
     * 问题描述
     */
    @Excel(name = "问题描述")
    private String rqstcontent;

    /**
     * 相关附件
     */
    @Excel(name = "相关附件")
    private String attachfilename;

    /**
     * 更新时间
     */
    @Excel(name = "")
    private Date updatedate;

    /**
     * 办理跟踪表
     */
    @Excel(name = "办理跟踪表")
    //隐藏,不用再mapper.xml文件和数据表中添加相应字段
    @TableField(exist = false)
    private List<Map<String, Opinion>> opinion;

    /**
     * 区域
     */
    @Excel(name = "区域")
    private String region;

    /**
     * 关键字
     */
    @Excel(name = "关键字")
    private String keyword;


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
     * 涉及主体名称
     */
    @Excel(name = "涉及主体名称")
    private String entname;

    /**
     * 最终反馈日期
     */
    @Excel(name = "最终反馈日期")
    private Date finalfeedbackdate;

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

    public String getInformationopen() {
        return informationopen;
    }

    public void setInformationopen(String informationopen) {
        this.informationopen = informationopen;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getReflectiontype() {
        return reflectiontype;
    }

    public void setReflectiontype(String reflectiontype) {
        this.reflectiontype = reflectiontype;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAppealsource() {
        return appealsource;
    }

    public void setAppealsource(String appealsource) {
        this.appealsource = appealsource;
    }

    public String getRqsttitle() {
        return rqsttitle;
    }

    public void setRqsttitle(String rqsttitle) {
        this.rqsttitle = rqsttitle;
    }

    public String getRqstcontent() {
        return rqstcontent;
    }

    public void setRqstcontent(String rqstcontent) {
        this.rqstcontent = rqstcontent;
    }

    public String getAttachfilename() {
        return attachfilename;
    }

    public void setAttachfilename(String attachfilename) {
        this.attachfilename = attachfilename;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public List<Map<String, Opinion>> getOpinion() {
        return opinion;
    }

    public void setOpinion(List<Map<String, Opinion>> opinion) {
        this.opinion = opinion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getEntname() {
        return entname;
    }

    public void setEntname(String entname) {
        this.entname = entname;
    }

    public Date getFinalfeedbackdate() {
        return finalfeedbackdate;
    }

    public void setFinalfeedbackdate(Date finalfeedbackdate) {
        this.finalfeedbackdate = finalfeedbackdate;
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

    @Override
    public String toString() {
        return "Consultationform{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", tserialnum='" + tserialnum + '\'' +
                ", formtype='" + formtype + '\'' +
                ", businesstypecodeid='" + businesstypecodeid + '\'' +
                ", businesstype='" + businesstype + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", rqstnumber='" + rqstnumber + '\'' +
                ", linknumber='" + linknumber + '\'' +
                ", informationopen='" + informationopen + '\'' +
                ", address='" + address + '\'' +
                ", usertype='" + usertype + '\'' +
                ", focus='" + focus + '\'' +
                ", reflectiontype='" + reflectiontype + '\'' +
                ", category='" + category + '\'' +
                ", appealsource='" + appealsource + '\'' +
                ", rqsttitle='" + rqsttitle + '\'' +
                ", rqstcontent='" + rqstcontent + '\'' +
                ", attachfilename='" + attachfilename + '\'' +
                ", updatedate=" + updatedate +
                ", opinion=" + opinion +
                ", region='" + region + '\'' +
                ", keyword='" + keyword + '\'' +
                ", transfernumber='" + transfernumber + '\'' +
                ", incomingcommpany='" + incomingcommpany + '\'' +
                ", isprofessional='" + isprofessional + '\'' +
                ", consultantaddress='" + consultantaddress + '\'' +
                ", processingunit='" + processingunit + '\'' +
                ", acceptdate=" + acceptdate +
                ", entname='" + entname + '\'' +
                ", finalfeedbackdate=" + finalfeedbackdate +
                ", complaintstype='" + complaintstype + '\'' +
                ", complaintstypecodeid='" + complaintstypecodeid + '\'' +
                ", registration='" + registration + '\'' +
                ", undertake='" + undertake + '\'' +
                ", satisfied='" + satisfied + '\'' +
                '}';
    }
}
