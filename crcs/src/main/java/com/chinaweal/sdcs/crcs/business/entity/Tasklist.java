package com.chinaweal.sdcs.crcs.business.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-08-18
 * 待办信息表
 */
@ExcelTarget(value = "Tasklist")
public class Tasklist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Excel(name = "序号", orderNum = "0" ,width =10 )
    private String id;

    /**
     * 业务主键
     */
    private String caseguid;

    /**
     * 工单分类
     */
    @Excel(name = "工单分类", orderNum = "1" ,width =15 )
    private String focus;

    /**
     * 表单号
     */
    private String serialnum;

    /**
     * 工单号
     */
    @Excel(name = "工单号", orderNum = "2", width = 20)
    private String tserialnum;

    /**
     * 工单标题
     */
    @Excel(name = "工单标题", orderNum = "3", width = 40)
    private String rqsttitle;

    /**
     * 业务分类
     */
    @Excel(name = "业务分类", orderNum = "4", width = 15)
    private String accordtype;

    /**
     * 政府标签
     */
    private String labelname;

    /**
     * 交办时间
     */
    @Excel(name = "交办时间", orderNum = "12", width = 30, format = "yyyy-MM-dd HH:mm ")
    private Date assigndate;

    /**
     * 到期时间
     */
    private Date expiredate;

    /**
     * 剩余时间
     */
    private String resulttimemin;

    /**
     * 系统来源
     */
    @Excel(name = "系统来源", orderNum = "13", width = 20)
    private String systemsource;

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
     * 业务类型
     */
    private String bustype;

    /**
     * 投诉人
     */
    @Excel(name = "投诉人", orderNum = "5", width = 15)
    private String complainant;

    /**
     * 被投诉人
     */
    @Excel(name = "被投诉人", orderNum = "6", width = 15)
    private String complainanted;

    /**
     * 电话
     */
    @Excel(name = "电话", orderNum = "7", width = 25)
    private String phone;

    /**
     * 地址
     */
    @Excel(name = "地址", orderNum = "8", width = 30)
    private String address;

    /**
     * 投诉内容
     */
    @Excel(name = "投诉内容", orderNum = "9", width = 20)
    private String content;

    /**
     * 处理结果
     */
    @Excel(name = "处理结果", orderNum = "10", width = 30)
    private String results;

    /**
     * 客体分类
     */
    @Excel(name = "客体分类", orderNum = "11", width = 15)
    private String classification;

    /**
     * 处理机关id
     */
    private String handleregion;

    /**
     * 处理部门id
     */
    private String handledeptid;

    /**
     * 处理部门名称
     */
    private String handledeptname;

    /**
     * 登记部门
     */
    private String regdeptname;

    /**
     * 登记人
     */
    private String registrant;

    /**
     * 催办次数
     */
    private String cwcs;

    /**
     * 督办次数
     */
    private String dubcs;

    /**
     * 初步排插期限
     */
    private Date firstblqx;

    /**
     * 办结期限
     */
    private Date blqx;

    /**
     * 当前处理机关名称
     */
    private String handleorgname;

    /**
     * 是否立案
     */
    private String isregistercase;

    /**
     * 工单办理开始时间
     */
    private Date taskstartdate;

    /**
     * 工单办理结束时间
     */
    private Date taskenddate;

    /**
     * 待办生成时间
     */
    private Date createdate;

    /**
     *立案时间
     */
    private Date filingdate;

    /**
     *是否重派
     */
    private String iscp;

    /**
     *是否新分派
     */
    private String isnewassign;

    /**
     * 初步答复状态
     */
    private String iscbdf;

    /**
     * 途经
     */
    private String appealsource;

    /**
     * 经济类商品/服务
     */
    private String objectcategory;

    /**
     * 投诉类商品/服务
     */
    private String servicetype;

    /**
     * 涉及问题分类
     */
    private String complaintstype;

    /**
     * 是否网购
     */
    /*private String isweb;*/

    /**
     * 初步反馈
     */
    private String feedback;


    public String getIscbdf() {
        return iscbdf;
    }

    public void setIscbdf(String iscbdf) {
        this.iscbdf = iscbdf;
    }

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
    public Date getAssigndate() {
        return assigndate;
    }

    public void setAssigndate(Date assigndate) {
        this.assigndate = assigndate;
    }

    public String getResulttimemin() {
        return resulttimemin;
    }

    public void setResulttimemin(String resulttimemin) {
        this.resulttimemin = resulttimemin;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }
    public String getSystemsource() {
        return systemsource;
    }

    public void setSystemsource(String systemsource) {
        this.systemsource = systemsource;
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

    public String getComplainant() {
        return complainant;
    }

    public void setComplainant(String complainant) {
        this.complainant = complainant;
    }

    public String getComplainanted() {
        return complainanted;
    }

    public void setComplainanted(String complainanted) {
        this.complainanted = complainanted;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
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

    public String getHandledeptname() {
        return handledeptname;
    }

    public void setHandledeptname(String handledeptname) {
        this.handledeptname = handledeptname;
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

    public String getCwcs() {
        return cwcs;
    }

    public void setCwcs(String cwcs) {
        this.cwcs = cwcs;
    }

    public String getDubcs() {
        return dubcs;
    }

    public void setDubcs(String dubcs) {
        this.dubcs = dubcs;
    }

    public Date getFirstblqx() {
        return firstblqx;
    }

    public void setFirstblqx(Date firstblqx) {
        this.firstblqx = firstblqx;
    }

    public Date getBlqx() {
        return blqx;
    }

    public void setBlqx(Date blqx) {
        this.blqx = blqx;
    }

    public String getHandleorgname() {
        return handleorgname;
    }

    public void setHandleorgname(String handleorgname) {
        this.handleorgname = handleorgname;
    }

    public String getIsregistercase() {
        return isregistercase;
    }

    public void setIsregistercase(String isregistercase) {
        this.isregistercase = isregistercase;
    }

    public Date getTaskstartdate() {
        return taskstartdate;
    }

    public void setTaskstartdate(Date taskstartdate) {
        this.taskstartdate = taskstartdate;
    }

    public Date getTaskenddate() {
        return taskenddate;
    }

    public void setTaskenddate(Date taskenddate) {
        this.taskenddate = taskenddate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getFilingdate() {
        return filingdate;
    }

    public void setFilingdate(Date filingdate) {
        this.filingdate = filingdate;
    }

    public String getIsnewassign() {
        return isnewassign;
    }

    public void setIsnewassign(String isnewassign) {
        this.isnewassign = isnewassign;
    }

    public String getAppealsource() {
        return appealsource;
    }

    public void setAppealsource(String appealsource) {
        this.appealsource = appealsource;
    }

    public String getObjectcategory() {
        return objectcategory;
    }

    public void setObjectcategory(String objname) {
        this.objectcategory = objname;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getComplaintstype() {
        return complaintstype;
    }

    public void setComplaintstype(String complaintstype) {
        this.complaintstype = complaintstype;
    }

    /*public String getIsweb() {
        return isweb;
    }

    public void setIsweb(String isweb) {
        this.isweb = isweb;
    }
*/


    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    @Override
    public String toString() {
        return "Tasklist{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", focus='" + focus + '\'' +
                ", serialnum='" + serialnum + '\'' +
                ", tserialnum='" + tserialnum + '\'' +
                ", rqsttitle='" + rqsttitle + '\'' +
                ", accordtype='" + accordtype + '\'' +
                ", labelname='" + labelname + '\'' +
                ", assigndate=" + assigndate +
                ", expiredate=" + expiredate +
                ", resulttimemin='" + resulttimemin + '\'' +
                ", systemsource='" + systemsource + '\'' +
                ", issignin='" + issignin + '\'' +
                ", signinaccount='" + signinaccount + '\'' +
                ", signinname='" + signinname + '\'' +
                ", signinuserid='" + signinuserid + '\'' +
                ", signindeptid='" + signindeptid + '\'' +
                ", signdate=" + signdate +
                ", formtype='" + formtype + '\'' +
                ", operate='" + operate + '\'' +
                ", nextnodeid='" + nextnodeid + '\'' +
                ", state='" + state + '\'' +
                ", updatedate=" + updatedate +
                ", ismain='" + ismain + '\'' +
                ", fromnodeid='" + fromnodeid + '\'' +
                ", isaccept='" + isaccept + '\'' +
                ", senderid='" + senderid + '\'' +
                ", sdexpirationdate=" + sdexpirationdate +
                ", worktype='" + worktype + '\'' +
                ", regionid='" + regionid + '\'' +
                ", bustype='" + bustype + '\'' +
                ", complainant='" + complainant + '\'' +
                ", complainanted='" + complainanted + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", results='" + results + '\'' +
                ", classification='" + classification + '\'' +
                ", handleregion='" + handleregion + '\'' +
                ", handledeptid='" + handledeptid + '\'' +
                ", handledeptname='" + handledeptname + '\'' +
                ", regdeptname='" + regdeptname + '\'' +
                ", registrant='" + registrant + '\'' +
                ", cwcs='" + cwcs + '\'' +
                ", dubcs='" + dubcs + '\'' +
                ", firstblqx=" + firstblqx +
                ", blqx=" + blqx +
                ", handleorgname='" + handleorgname + '\'' +
                ", isregistercase='" + isregistercase + '\'' +
                ", taskstartdate=" + taskstartdate +
                ", taskenddate=" + taskenddate +
                ", createdate=" + createdate +
                ", filingdate=" + filingdate +
                ", iscp=" + iscp +
                ", isnewassign=" + isnewassign +
                ", iscbdf=" + iscbdf +
                ", appealsource=" + appealsource +
                ", objectcategory =" + objectcategory +
                ", servicetype =" + servicetype +
                ", complaintstype =" + complaintstype +
                ", feedback =" + feedback +
                /*", isweb =" + isweb +*/
                '}';
    }
}
