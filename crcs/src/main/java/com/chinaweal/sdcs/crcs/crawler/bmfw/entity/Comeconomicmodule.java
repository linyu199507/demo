package com.chinaweal.sdcs.crcs.crawler.bmfw.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-08-20
 */
public class Comeconomicmodule implements Serializable {

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
     * 表单类型
     */
    private String formtype;

    /**
     * 标题
     */
    private String rqsttitle;

    /**
     * 表单模板
     */
    private String accordtype;

    /**
     * 姓名
     */
    private String rqstperson;

    /**
     * 性别
     */
    private String gender;

    /**
     * 来电号码
     */
    private String rqstnumber;

    /**
     * 联系电话
     */
    private String linknumber;

    /**
     * 举报人地址
     */
    private String rqstaddress;

    /**
     * 反映类型
     */
    private String usertype;

    /**
     * 回复方式
     */
    private String answertype;

    /**
     * 工单分类
     */
    private String focus;

    /**
     * 类别
     */
    private String providelb;

    /**
     * 年龄
     */
    private String age;

    /**
     * 民族
     */
    private String nation;

    /**
     * 联系地址
     */
    private String contactaddress;

    /**
     * 身份
     */
    private String status;

    /**
     * 投诉票据
     */
    private String complaintpaper;

    /**
     * 残疾人标识
     */
    private String disabilityidentification;

    /**
     * 证件类型
     */
    private String certificate;

    /**
     * 证件号码
     */
    private String idnumber;

    /**
     * 职业
     */
    private String career;

    /**
     * 工作单位
     */
    private String company;

    /**
     * 涉及主体名称
     */
    private String subjectname;

    /**
     * 联系方式
     */
    private String linktype;

    /**
     * 号码
     */
    private String subjectphone;

    /**
     * 管辖单位
     */
    private String aicstationid;

    /**
     * 邮编
     */
    private String postcode;

    /**
     * 经营地址区
     */
    private String jyareacode;

    /**
     * 经营地址详细
     */
    private String subjectaddress;

    /**
     * 实际注册地址区
     */
    private String zcareacode;

    /**
     * 实际注册地址详细
     */
    private String subjectzcadd;

    /**
     * 核查名称
     */
    private String cksubjectname;

    /**
     * 核查经营地址区
     */
    private String ckjyareacode;

    /**
     * 核查经营地址详细
     */
    private String cksubjectaddress;

    /**
     * 商品/服务名称
     */
    private String objname;

    /**
     * 商品品牌
     */
    private String brand;

    /**
     * 涉及客体分类
     */
    private String objclassify;

    /**
     * 涉及金额
     */
    private BigDecimal objmoney;

    /**
     * 生产企业
     */
    private String producecompany;

    /**
     * 是否网购
     */
    private String isweb;

    /**
     * 商品进口标志
     */
    private String importmark;

    /**
     * 型号规格
     */
    private String typespecification;

    /**
     * 商品数量
     */
    private String quantity;

    /**
     * 计量单位
     */
    private String measureunit;

    /**
     * 商品服务价格
     */
    private BigDecimal price;

    /**
     * 购买日期
     */
    private Date purchasingdate;

    /**
     * 涉及问题分类
     */
    private String problemtype;

    /**
     * 涉嫌违法行为
     */
    private String illegalrules;

    /**
     * 问题陈述
     */
    private String problemdescribe;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 事件级别
     */
    private String eventlevel;

    /**
     * 政务标签
     */
    private String govlabel;

    /**
     * 具体业务分类
     */
    private String differserv;

    /**
     * 简要情况
     */
    private String briefing;

    /**
     * 案值
     */
    private BigDecimal anestimate;

    /**
     * 经济损失值
     */
    private BigDecimal economicloss;

    /**
     * 侵权分类
     */
    private String tortclassify;

    /**
     * 人事伤害类型
     */
    private String personalinjury;

    /**
     * 人事伤害确人程度
     */
    private String injurydegree;

    /**
     * 受伤人数
     */
    private String injuries;

    /**
     * 消费安全确认程度
     */
    private String consumptionsafety;

    /**
     * 消费安全类型
     */
    private String consumptionsafetytype;

    /**
     * 绝产亩数
     */
    private String even;

    /**
     * 减产亩数
     */
    private String reduction;

    /**
     * 投诉要求
     */
    private String report;

    /**
     * 是否出现场
     */
    private String isoutmall;

    /**
     * 是否反馈
     */
    private String isfeedback;

    /**
     * 是否需要奖励
     */
    private String isaward;

    /**
     * 相关附件名称
     */
    private String attachfilename;

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
    public String getRqstperson() {
        return rqstperson;
    }

    public void setRqstperson(String rqstperson) {
        this.rqstperson = rqstperson;
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
    public String getRqstaddress() {
        return rqstaddress;
    }

    public void setRqstaddress(String rqstaddress) {
        this.rqstaddress = rqstaddress;
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
    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }
    public String getProvidelb() {
        return providelb;
    }

    public void setProvidelb(String providelb) {
        this.providelb = providelb;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
    public String getContactaddress() {
        return contactaddress;
    }

    public void setContactaddress(String contactaddress) {
        this.contactaddress = contactaddress;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getComplaintpaper() {
        return complaintpaper;
    }

    public void setComplaintpaper(String complaintpaper) {
        this.complaintpaper = complaintpaper;
    }
    public String getDisabilityidentification() {
        return disabilityidentification;
    }

    public void setDisabilityidentification(String disabilityidentification) {
        this.disabilityidentification = disabilityidentification;
    }
    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }
    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }
    public String getLinktype() {
        return linktype;
    }

    public void setLinktype(String linktype) {
        this.linktype = linktype;
    }
    public String getSubjectphone() {
        return subjectphone;
    }

    public void setSubjectphone(String subjectphone) {
        this.subjectphone = subjectphone;
    }
    public String getAicstationid() {
        return aicstationid;
    }

    public void setAicstationid(String aicstationid) {
        this.aicstationid = aicstationid;
    }
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getJyareacode() {
        return jyareacode;
    }

    public void setJyareacode(String jyareacode) {
        this.jyareacode = jyareacode;
    }
    public String getSubjectaddress() {
        return subjectaddress;
    }

    public void setSubjectaddress(String subjectaddress) {
        this.subjectaddress = subjectaddress;
    }
    public String getZcareacode() {
        return zcareacode;
    }

    public void setZcareacode(String zcareacode) {
        this.zcareacode = zcareacode;
    }
    public String getSubjectzcadd() {
        return subjectzcadd;
    }

    public void setSubjectzcadd(String subjectzcadd) {
        this.subjectzcadd = subjectzcadd;
    }
    public String getCksubjectname() {
        return cksubjectname;
    }

    public void setCksubjectname(String cksubjectname) {
        this.cksubjectname = cksubjectname;
    }
    public String getCkjyareacode() {
        return ckjyareacode;
    }

    public void setCkjyareacode(String ckjyareacode) {
        this.ckjyareacode = ckjyareacode;
    }
    public String getCksubjectaddress() {
        return cksubjectaddress;
    }

    public void setCksubjectaddress(String cksubjectaddress) {
        this.cksubjectaddress = cksubjectaddress;
    }
    public String getObjname() {
        return objname;
    }

    public void setObjname(String objname) {
        this.objname = objname;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getObjclassify() {
        return objclassify;
    }

    public void setObjclassify(String objclassify) {
        this.objclassify = objclassify;
    }
    public BigDecimal getObjmoney() {
        return objmoney;
    }

    public void setObjmoney(BigDecimal objmoney) {
        this.objmoney = objmoney;
    }
    public String getProducecompany() {
        return producecompany;
    }

    public void setProducecompany(String producecompany) {
        this.producecompany = producecompany;
    }
    public String getIsweb() {
        return isweb;
    }

    public void setIsweb(String isweb) {
        this.isweb = isweb;
    }
    public String getImportmark() {
        return importmark;
    }

    public void setImportmark(String importmark) {
        this.importmark = importmark;
    }
    public String getTypespecification() {
        return typespecification;
    }

    public void setTypespecification(String typespecification) {
        this.typespecification = typespecification;
    }
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getMeasureunit() {
        return measureunit;
    }

    public void setMeasureunit(String measureunit) {
        this.measureunit = measureunit;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Date getPurchasingdate() {
        return purchasingdate;
    }

    public void setPurchasingdate(Date purchasingdate) {
        this.purchasingdate = purchasingdate;
    }
    public String getProblemtype() {
        return problemtype;
    }

    public void setProblemtype(String problemtype) {
        this.problemtype = problemtype;
    }
    public String getIllegalrules() {
        return illegalrules;
    }

    public void setIllegalrules(String illegalrules) {
        this.illegalrules = illegalrules;
    }
    public String getProblemdescribe() {
        return problemdescribe;
    }

    public void setProblemdescribe(String problemdescribe) {
        this.problemdescribe = problemdescribe;
    }
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getEventlevel() {
        return eventlevel;
    }

    public void setEventlevel(String eventlevel) {
        this.eventlevel = eventlevel;
    }
    public String getGovlabel() {
        return govlabel;
    }

    public void setGovlabel(String govlabel) {
        this.govlabel = govlabel;
    }
    public String getDifferserv() {
        return differserv;
    }

    public void setDifferserv(String differserv) {
        this.differserv = differserv;
    }
    public String getBriefing() {
        return briefing;
    }

    public void setBriefing(String briefing) {
        this.briefing = briefing;
    }
    public BigDecimal getAnestimate() {
        return anestimate;
    }

    public void setAnestimate(BigDecimal anestimate) {
        this.anestimate = anestimate;
    }
    public BigDecimal getEconomicloss() {
        return economicloss;
    }

    public void setEconomicloss(BigDecimal economicloss) {
        this.economicloss = economicloss;
    }
    public String getTortclassify() {
        return tortclassify;
    }

    public void setTortclassify(String tortclassify) {
        this.tortclassify = tortclassify;
    }
    public String getPersonalinjury() {
        return personalinjury;
    }

    public void setPersonalinjury(String personalinjury) {
        this.personalinjury = personalinjury;
    }
    public String getInjurydegree() {
        return injurydegree;
    }

    public void setInjurydegree(String injurydegree) {
        this.injurydegree = injurydegree;
    }
    public String getInjuries() {
        return injuries;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }
    public String getConsumptionsafety() {
        return consumptionsafety;
    }

    public void setConsumptionsafety(String consumptionsafety) {
        this.consumptionsafety = consumptionsafety;
    }
    public String getConsumptionsafetytype() {
        return consumptionsafetytype;
    }

    public void setConsumptionsafetytype(String consumptionsafetytype) {
        this.consumptionsafetytype = consumptionsafetytype;
    }
    public String getEven() {
        return even;
    }

    public void setEven(String even) {
        this.even = even;
    }
    public String getReduction() {
        return reduction;
    }

    public void setReduction(String reduction) {
        this.reduction = reduction;
    }
    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
    public String getIsoutmall() {
        return isoutmall;
    }

    public void setIsoutmall(String isoutmall) {
        this.isoutmall = isoutmall;
    }
    public String getIsfeedback() {
        return isfeedback;
    }

    public void setIsfeedback(String isfeedback) {
        this.isfeedback = isfeedback;
    }
    public String getIsaward() {
        return isaward;
    }

    public void setIsaward(String isaward) {
        this.isaward = isaward;
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

    @Override
    public String toString() {
        return "Comeconomicmodule{" +
        "id=" + id +
        ", caseguid=" + caseguid +
        ", formtype=" + formtype +
        ", rqsttitle=" + rqsttitle +
        ", accordtype=" + accordtype +
        ", rqstperson=" + rqstperson +
        ", gender=" + gender +
        ", rqstnumber=" + rqstnumber +
        ", linknumber=" + linknumber +
        ", rqstaddress=" + rqstaddress +
        ", usertype=" + usertype +
        ", answertype=" + answertype +
        ", focus=" + focus +
        ", providelb=" + providelb +
        ", age=" + age +
        ", nation=" + nation +
        ", contactaddress=" + contactaddress +
        ", status=" + status +
        ", complaintpaper=" + complaintpaper +
        ", disabilityidentification=" + disabilityidentification +
        ", certificate=" + certificate +
        ", idnumber=" + idnumber +
        ", career=" + career +
        ", company=" + company +
        ", subjectname=" + subjectname +
        ", linktype=" + linktype +
        ", subjectphone=" + subjectphone +
        ", aicstationid=" + aicstationid +
        ", postcode=" + postcode +
        ", jyareacode=" + jyareacode +
        ", subjectaddress=" + subjectaddress +
        ", zcareacode=" + zcareacode +
        ", subjectzcadd=" + subjectzcadd +
        ", cksubjectname=" + cksubjectname +
        ", ckjyareacode=" + ckjyareacode +
        ", cksubjectaddress=" + cksubjectaddress +
        ", objname=" + objname +
        ", brand=" + brand +
        ", objclassify=" + objclassify +
        ", objmoney=" + objmoney +
        ", producecompany=" + producecompany +
        ", isweb=" + isweb +
        ", importmark=" + importmark +
        ", typespecification=" + typespecification +
        ", quantity=" + quantity +
        ", measureunit=" + measureunit +
        ", price=" + price +
        ", purchasingdate=" + purchasingdate +
        ", problemtype=" + problemtype +
        ", illegalrules=" + illegalrules +
        ", problemdescribe=" + problemdescribe +
        ", keyword=" + keyword +
        ", eventlevel=" + eventlevel +
        ", govlabel=" + govlabel +
        ", differserv=" + differserv +
        ", briefing=" + briefing +
        ", anestimate=" + anestimate +
        ", economicloss=" + economicloss +
        ", tortclassify=" + tortclassify +
        ", personalinjury=" + personalinjury +
        ", injurydegree=" + injurydegree +
        ", injuries=" + injuries +
        ", consumptionsafety=" + consumptionsafety +
        ", consumptionsafetytype=" + consumptionsafetytype +
        ", even=" + even +
        ", reduction=" + reduction +
        ", report=" + report +
        ", isoutmall=" + isoutmall +
        ", isfeedback=" + isfeedback +
        ", isaward=" + isaward +
        ", attachfilename=" + attachfilename +
        ", updatedate=" + updatedate +
        "}";
    }
}
