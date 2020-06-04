package com.chinaweal.sdcs.crcs.business.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class Business12345VO {

    /**
     * 主键
     */
    @Excel(name = "序号", orderNum = "0" ,width =5 )
    private String id;

    /**
     * 业务主键
     */
    private String caseguid;


    /**
     * 业务类型
     */
    @Excel(name = "业务类型", orderNum = "1", width = 20)
    private String formtype;

    /**
     * 表单号
     */
    @Excel(name = "表单号", orderNum = "2", width = 20)
    private String serialnum;

    /**
     * 工单号
     */
    @Excel(name = "工单号", orderNum = "3", width = 20)
    private String tserialnum;

    /**
     * 工单标题
     */
    @Excel(name = "工单标题", orderNum = "4", width = 25)
    private String rqsttitle;

    /**
     * 登记内容
     */
    @Excel(name = "登记内容", orderNum = "5", width = 30)
    private String content;

    /**
     * 工单状态/当前环节
     */
    @Excel(name = "当前环节", orderNum = "6", width = 15)
    private String operate;

    /**
     * 交办时间
     */
    @Excel(name = "交办时间", orderNum = "7", width = 20, format = "yyyy-MM-dd HH:mm:ss ")
    private Date assigndate;

    /**
     * 到期时间
     */
//    @Excel(name = "到期时间", orderNum = "8", width = 20, format = "yyyy-MM-dd HH:mm:ss ")
    private Date expiredate;

    /**
     * 诉求来源
     */
//    @Excel(name = "诉求来源", orderNum = "6", width = 15)
    private String systemsource;

    /**
     * 来电号码
     */
    @Excel(name = "来电号码", orderNum = "9", width = 20)
    private String phone;

    /**
     * 来电人姓名
     */
//    @Excel(name = "来电人姓名", orderNum = "9", width = 15)
    private String complainant;

    /**
     * 业务分类
     */
//    @Excel(name = "业务分类", orderNum = "10", width = 15)
    private String accordtype;


    /**
     * 关键字
     */
//    @Excel(name = "关键字", orderNum = "10", width = 15)
    private String keyword;

    /**
     * 涉及主体名称
     */
//    @Excel(name = "涉及主体名称", orderNum = "11", width = 20)
    private String complainanted;

    /**
     * 处理部门
     */
//    @Excel(name = "处理部门", orderNum = "12", width = 20)
    private String handledeptname;

    /**
     * 处理结果
     */
//    @Excel(name = "处理结果", orderNum = "13", width = 30)
    private String results;

    /**
     * 商品品牌
     */
//    @Excel(name = "商品品牌", orderNum = "14", width = 15)
    private String brand;

    /**
     * 挽回经济损失
     */
//    @Excel(name = "挽回经济损失", orderNum = "15", width = 10)
    private BigDecimal financialloss;

    /**
     * 加倍赔偿金额
     */
//    @Excel(name = "加倍赔偿金额", orderNum = "16", width = 10)
    private BigDecimal doublemoney;

    /**
     * 经营地址区域
     */
//    @Excel(name = "经营地址区域", orderNum = "17", width = 10)
    private String jyareacode;

    /**
     * 经营地址详细地址
     */
//    @Excel(name = "经营地址详细地址", orderNum = "18", width = 25)
    private String address;

    /**
     * 涉及客体分类
     */
//    @Excel(name = "涉及客体分类", orderNum = "19", width = 20)
    private String classification;

    /**
     * 涉及问题分类
     */
//    @Excel(name = "涉及问题分类", orderNum = "20", width = 20)
    private String problemtype;

    /**
     * 是否诉转案
     */
//    @Excel(name = "是否诉转案", orderNum = "21", width = 10)
    private String applealtocase;

    /**
     * 业务性质
     */
//    @Excel(name = "业务性质", orderNum = "22", width = 10)
    private String bustype;

    /**
     * 政府标签
     */
    private String labelname;

    /**
     * 剩余时间
     */
    private String resulttimemin;

    /**
     * 工单分类
     */
    private String focus;

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
     * 操作节点英文用于判断节点
     */
    private String nextnodeid;

    /**
     * 是否有效（1有效 0失效）
     */
    private String state;

    /**
     * 更新时间
     */
    private Date updatedate;

    /**
     * 是否主管
     */
    private String ismain;

    /**
     * 上一步处理节点
     */
    private String fromnodeid;

    /**
     * 是否已受理(1已受理 0未受理)
     */
    private String isaccept;

    /**
     * 发送人的id
     */
    private String senderid;

    /**
     * 新增到期时间 2019-0921
     */
    @Excel(name = "到期时间", orderNum = "8", width = 20, format = "yyyy-MM-dd HH:mm:ss ")
    private Date sdexpirationdate;

    /**
     * 新增工单分类
     */
    private String worktype;

    /**
     * 机关代码id
     */
    private String regionid;

    /**
     * 处理机关id
     */
    private String handleregion;

    /**
     * 处理部门id
     */
    private String handledeptid;


    /**
     * 登记部门
     */
    private String regdeptname;

    /**
     * 登记人
     */
    private String registrant;


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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAssigndate() {
        return assigndate;
    }

    public void setAssigndate(Date assigndate) {
        this.assigndate = assigndate;
    }

    public String getSystemsource() {
        return systemsource;
    }

    public void setSystemsource(String systemsource) {
        this.systemsource = systemsource;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getComplainant() {
        return complainant;
    }

    public void setComplainant(String complainant) {
        this.complainant = complainant;
    }

    public String getAccordtype() {
        return accordtype;
    }

    public void setAccordtype(String accordtype) {
        this.accordtype = accordtype;
    }

    public String getComplainanted() {
        return complainanted;
    }

    public void setComplainanted(String complainanted) {
        this.complainanted = complainanted;
    }

    public String getHandledeptname() {
        return handledeptname;
    }

    public void setHandledeptname(String handledeptname) {
        this.handledeptname = handledeptname;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getFinancialloss() {
        return financialloss;
    }

    public void setFinancialloss(BigDecimal financialloss) {
        this.financialloss = financialloss;
    }

    public BigDecimal getDoublemoney() {
        return doublemoney;
    }

    public void setDoublemoney(BigDecimal doublemoney) {
        this.doublemoney = doublemoney;
    }

    public String getJyareacode() {
        return jyareacode;
    }

    public void setJyareacode(String jyareacode) {
        this.jyareacode = jyareacode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getProblemtype() {
        return problemtype;
    }

    public void setProblemtype(String problemtype) {
        this.problemtype = problemtype;
    }

    public String getApplealtocase() {
        return applealtocase;
    }

    public void setApplealtocase(String applealtocase) {
        this.applealtocase = applealtocase;
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

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
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

    public String getNextnodeid() {
        return nextnodeid;
    }

    public void setNextnodeid(String nextnodeid) {
        this.nextnodeid = nextnodeid;
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

    public String getIsmain() {
        return ismain;
    }

    public void setIsmain(String ismain) {
        this.ismain = ismain;
    }

    public String getFromnodeid() {
        return fromnodeid;
    }

    public void setFromnodeid(String fromnodeid) {
        this.fromnodeid = fromnodeid;
    }

    public String getIsaccept() {
        return isaccept;
    }

    public void setIsaccept(String isaccept) {
        this.isaccept = isaccept;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public Date getSdexpirationdate() {
        return sdexpirationdate;
    }

    public void setSdexpirationdate(Date sdexpirationdate) {
        this.sdexpirationdate = sdexpirationdate;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public String getHandleregion() {
        return handleregion;
    }

    public void setHandleregion(String handleregion) {
        this.handleregion = handleregion;
    }

    public String getHandledeptid() {
        return handledeptid;
    }

    public void setHandledeptid(String handledeptid) {
        this.handledeptid = handledeptid;
    }

    public String getRegdeptname() {
        return regdeptname;
    }

    public void setRegdeptname(String regdeptname) {
        this.regdeptname = regdeptname;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
