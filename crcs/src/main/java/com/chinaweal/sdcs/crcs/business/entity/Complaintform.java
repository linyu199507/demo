package com.chinaweal.sdcs.crcs.business.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-20
 */
public class Complaintform implements Serializable {

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
     * 标题
     */
    @Excel(name = "标题")
    private String rqsttitle;

    /**
     * 投诉人姓名
     */
    @Excel(name = "举报人姓名")
    private String rqstperson;

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
     * 类型
     */
    @Excel(name = "类型")
    private String providetype;

    /**
     * 类型
     */
    @Excel(name = "类型codeid")
    private String providetypecodeid;


    /**
     * 类别
     */
    @Excel(name = "类别")
    private String providelb;

    /**
     * 工单类型
     */
    @Excel(name = "工单分类")
    private String focus;

    /**
     * 区域
     */
    @Excel(name = "区域")
    private String region;

    /**
     * 年龄
     */
    @Excel(name = "年龄段")
    private String age;

    /**
     * 民族
     */
    @Excel(name = "民族")
    private String nation;

    /**
     * 身份
     */
    @Excel(name = "身份")
    private String status;

    /**
     * 投诉票据
     */
    @Excel(name = "投诉票据")
    private String complaintpaper;

    /**
     * 残疾人标识
     */
    @Excel(name = "")
    private String disabilityidentification;

    /**
     * 证件类型
     */
    @Excel(name = "证件类型")
    private String certificate;

    /**
     * 邮政编码
     */
    @Excel(name = "邮政编码")
    private String codepostal;

    /**
     * 证件号码
     */
    @Excel(name = "证件号码")
    private String idnumber;

    /**
     * 职业
     */
    @Excel(name = "职业")
    private String career;

    /**
     * 工作单位
     */
    @Excel(name = "工作单位")
    private String company;

    /**
     * 名称
     */
    @Excel(name = "涉及主体名称")
    private String subjectname;

    /**
     * 联系方式
     */
    @Excel(name = "联系方式")
    private String linktype;

    /**
     * 电话
     */
    @Excel(name = "电话")
    private String subjectphone;

    /**
     * 管辖单位
     */
    @Excel(name = "")
    private String aicstationid;

    /**
     * 邮编
     */
    @Excel(name = "邮编")
    private String postcode;

    /**
     * 经营地址区
     */
    @Excel(name = "经营地址区")
    private String jyareacode;

    /**
     * 经营地址
     */
    @Excel(name = "地址")
    private String subjectaddress;

    /**
     * 实际注册地址区
     */
    @Excel(name = "实际注册地址区")
    private String zcareacode;

    /**
     * 实际注册地址
     */
    @Excel(name = "实际注册地址")
    private String subjectzcadd;

    /**
     * 核查名称
     */
    @Excel(name = "核查名称")
    private String cksubjectname;

    /**
     * 核查经营地址区
     */
    @Excel(name = "核查经营地址区")
    private String ckjyareacode;

    /**
     * 核查经营地址
     */
    @Excel(name = "核查经营地址")
    private String cksubjectaddress;

    /**
     * 商品/服务名称
     */
    @Excel(name = "商品/服务名称")
    private String objname;

    /**
     * 商品品牌
     */
    @Excel(name = "品牌")
    private String brand;

    /**
     * 涉及客体分类
     */
    @Excel(name = "涉及客体分类")
    private String objclassify;

    /**
     * 涉及客体分类codeid
     */
    @Excel(name = "涉及客体分类codeid")
    private String objclassifycodeid;


    /**
     * 涉及金额
     */
    @Excel(name = "消费金额")
    private BigDecimal objmoney;

    /**
     * 生产企业
     */
    @Excel(name = "生产企业")
    private String producecompany;

    /**
     * 是否网购
     */
    @Excel(name = "是否网购")
    private String isweb;

    /**
     * 商品进口标志
     */
    @Excel(name = "商品进口标志")
    private String importmark;

    /**
     * 型号规格
     */
    @Excel(name = "型号规格")
    private String typespecification;

    /**
     * 商品数量
     */
    @Excel(name = "数量")
    private String quantity;

    /**
     * 计量单位
     */
    @Excel(name = "计量单位")
    private String measureunit;

    /**
     * 商品服务价格
     */
    @Excel(name = "商品服务价格")
    private BigDecimal price;

    /**
     * 购买日期
     */
    @Excel(name = "购买日期")
    private Date purchasingdate;

    /**
     * 涉及问题分类
     */
    @Excel(name = "问题分类")
    private String problemtype;


    /**
     * 问题陈述
     */
    @Excel(name = "投诉内容")
    private String problemdescribe;

    /**
     * 关键字
     */
    @Excel(name = "关键字")
    private String keyword;

    /**
     * 政务标签
     */
    @Excel(name = "政务标签")
    private String govlabeloutput;

    /**
     * 具体业务分类
     */
    @Excel(name = "具体业务分类")
    private String differserv;

    /**
     * 简要情况
     */
    @Excel(name = "简要情况")
    private String briefing;

    /**
     * 案值
     */
    @Excel(name = "案值")
    private BigDecimal anestimate;

    /**
     * 经济损失值
     */
    @Excel(name = "")
    private BigDecimal economicloss;

    /**
     * 侵权分类
     */
    @Excel(name = "侵权分类")
    private String tortclassify;

    /**
     * 人事伤害类型
     */
    @Excel(name = "")
    private String personalinjury;

    /**
     * 人事伤害确人程度
     */
    @Excel(name = "")
    private String injurydegree;

    /**
     * 受伤人数
     */
    @Excel(name = "")
    private String injuries;

    /**
     * 消费安全确认程度
     */
    @Excel(name = "")
    private String consumptionsafety;

    /**
     * 消费安全类型
     */
    @Excel(name = "消费安全类型")
    private String consumptionsafetytype;

    /**
     * 绝产亩数
     */
    @Excel(name = "")
    private String even;

    /**
     * 减产亩数
     */
    @Excel(name = "")
    private String reduction;

    /**
     * 投诉要求
     */
    @Excel(name = "投诉内容")
    private String report;

    /**
     * 是否出现场
     */
    @Excel(name = "是否出现场")
    private String isoutmall;

    /**
     * 相关附件名称
     */
    @Excel(name = "相关附件名称")
    private String attachfilename;

    /**
     * 诉求来源
     */
    @Excel(name = "信息来源")
    private String appealsource;

    /**
     * 信息公开
     */
    @Excel(name = "信息公开")
    private String informationopen;

    /**
     * 业务分类codeid------12315:行业分类codeid
     */
    @Excel(name = "业务分类codeid")
    private String businesstypecodeid;

    /**
     * 业务分类---------12315:行业分类
     */
    @Excel(name = "业务分类")
    private String businesstype;

    /**
     * 事件级别
     */
    @Excel(name = "事件级别")
    private String eventlevel;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间")
    private Date updatedate;


    /**
     * 举报人地址
     */
    @Excel(name = "投诉人地址")
    private String rqstaddress;


    /**
     * 登记编号
     */
    @Excel(name = "登记编号")
    private String registrationnumber;


    /**
     * 登记日期
     */
    @Excel(name = "登记日期")
    private Date recorddate;


    /**
     * 接收方式
     */
    @Excel(name = "接收方式")
    private String receivemode;


    /**
     * 联系地址
     */
    @Excel(name = "联系地址")
    private String linkaddress;


    /**
     * 电子邮箱地址
     */
    @Excel(name = "电子邮箱地址")
    private String emaiaddress;


    /**
     * 统一社会信用代码
     */
    @Excel(name = "统一社会信用代码")
    private String registerno;


    /**
     * 市场主体类型codeid
     */
    @Excel(name = "市场主体类型codeid")
    private String entitytypecodeid;

    /**
     * 市场主体类型
     */
    @Excel(name = "市场主体类型")
    private String entitytype;


    /**
     * 购物方式
     */
    @Excel(name = "购物方式")
    private String shoppingmode;

    /**
     * 商品/服务类型codeid
     */
    @Excel(name = "商品/服务类型codeid")
    private String servicetypecodeid;

    /**
     * 商品/服务类型
     */
    @Excel(name = "商品/服务类型")
    private String servicetype;


    /**
     * 投诉问题类型codeid
     */
    @Excel(name = "涉及问题分类codeid")
    private String complaintstypecodeid;

    /**
     * 投诉类型
     */
    @Excel(name = "涉及问题分类")
    private String complaintstype;


    /**
     * 投诉内容
     */
    @Excel(name = "投诉内容")
    private String contentrs;


    /**
     * 争议发生时间
     */
    @Excel(name = "争议发生时间")
    private Date disputedate;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String ordernumber;

    /**
     * 国籍
     */
    @Excel(name = "国籍")
    private String nationality;

    /**
     * 其他联系方式
     */
    @Excel(name = "其他联系方式")
    private String othercontacts;

    /**
     * 行业分类
     */
    @Excel(name = "行业分类")
    private String industryname;

    /**
     * 行业分类codeid
     */
    private String industrynamecodeid;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String reportedcontact;


    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String productname;


    /**
     * 批准文号
     */
    @Excel(name = "批准文号")
    private String approvalnumber;

    /**
     * 产品批号
     */
    @Excel(name = "产品批号")
    private String lotnumber;

    /**
     * 销售企业
     */
    @Excel(name = "销售企业")
    private String salesenterprise;

    /**
     * 产品有效期
     */
    @Excel(name = "产品有效期")
    private Date expirationdate;

    /**
     * 案件接收日期
     */
    @Excel(name = "案件接收日期")
    private Date acceptdate;

    /**
     * 案件分派日期
     */
    @Excel(name = "案件分派日期")
    private Date assignmentdate;

    /**
     * 初步反馈日期
     */
    @Excel(name = "初步反馈日期")
    private Date feedbackdate;

    /**
     * 移送编号
     */
    @Excel(name = "移送编号")
    private String transfernumber;

    /**
     * 来件类型
     */
    @Excel(name = "来件类型")
    private String incommingtype;

    /**
     * 地区来源
     */
    @Excel(name = "地区来源")
    private String detailaddress;

    /**
     * 所属板块
     */
    @Excel(name = "所属板块")
    private String subordinateplate;

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
     * 处理单位
     */
    @Excel(name = "处理单位")
    private String dealcompany;

    /**
     * 领域
     */
    @Excel(name = "领域")
    private String filed;

    /**
     * 网购平台
     */
    @Excel(name = "网购平台")
    private String shoppingplatform;

    /**
     * 受理通知书号
     */
    @Excel(name = "受理通知书号")
    private String acceptancenumber;

    /**
     * 受理通知书号
     */
    @Excel(name = "案件转办书号")
    private String transferbooknumber;

    /**
     * 办理跟踪表
     */
    @Excel(name = "办理跟踪表")
    //隐藏,不用再mapper.xml文件和数据表中添加相应字段
    @TableField(exist = false)
    private List<Map<String, Opinion>> opinion;

    /**
     * 事发地
     */
    private String accsce;

    /**
     * 初查反馈期限
     */
    private Date feedbacktime;

    /**
     * 办理期限
     */
    private Date todealwithtime;

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
     * (新)业务分类---------12315系统转12345系统
     */
    @Excel(name = "(新)业务分类")
    private String businesstypenew;

    /**
     * (新)业务分类---------12315系统转12345系统
     */
    @Excel(name = "(新)业务分类")
    private String businesstypecodeidnew;

    public String getBusinesstypenew() {
        return businesstypenew;
    }

    public void setBusinesstypenew(String businesstypenew) {
        this.businesstypenew = businesstypenew;
    }

    public String getBusinesstypecodeidnew() {
        return businesstypecodeidnew;
    }

    public void setBusinesstypecodeidnew(String businesstypecodeidnew) {
        this.businesstypecodeidnew = businesstypecodeidnew;
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

    public String getRqsttitle() {
        return rqsttitle;
    }

    public void setRqsttitle(String rqsttitle) {
        this.rqsttitle = rqsttitle;
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

    public String getProvidetype() {
        return providetype;
    }

    public void setProvidetype(String providetype) {
        this.providetype = providetype;
    }

    public String getProvidetypecodeid() {
        return providetypecodeid;
    }

    public void setProvidetypecodeid(String providetypecodeid) {
        this.providetypecodeid = providetypecodeid;
    }

    public String getProvidelb() {
        return providelb;
    }

    public void setProvidelb(String providelb) {
        this.providelb = providelb;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
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

    public String getObjclassifycodeid() {
        return objclassifycodeid;
    }

    public void setObjclassifycodeid(String objclassifycodeid) {
        this.objclassifycodeid = objclassifycodeid;
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

    public String getGovlabeloutput() {
        return govlabeloutput;
    }

    public void setGovlabeloutput(String govlabeloutput) {
        this.govlabeloutput = govlabeloutput;
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

    public String getAttachfilename() {
        return attachfilename;
    }

    public void setAttachfilename(String attachfilename) {
        this.attachfilename = attachfilename;
    }

    public String getAppealsource() {
        return appealsource;
    }

    public void setAppealsource(String appealsource) {
        this.appealsource = appealsource;
    }

    public String getInformationopen() {
        return informationopen;
    }

    public void setInformationopen(String informationopen) {
        this.informationopen = informationopen;
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

    public String getEventlevel() {
        return eventlevel;
    }

    public void setEventlevel(String eventlevel) {
        this.eventlevel = eventlevel;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getRqstaddress() {
        return rqstaddress;
    }

    public void setRqstaddress(String rqstaddress) {
        this.rqstaddress = rqstaddress;
    }

    public String getRegistrationnumber() {
        return registrationnumber;
    }

    public void setRegistrationnumber(String registrationnumber) {
        this.registrationnumber = registrationnumber;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }

    public String getReceivemode() {
        return receivemode;
    }

    public void setReceivemode(String receivemode) {
        this.receivemode = receivemode;
    }

    public String getLinkaddress() {
        return linkaddress;
    }

    public void setLinkaddress(String linkaddress) {
        this.linkaddress = linkaddress;
    }

    public String getEmaiaddress() {
        return emaiaddress;
    }

    public void setEmaiaddress(String emaiaddress) {
        this.emaiaddress = emaiaddress;
    }

    public String getRegisterno() {
        return registerno;
    }

    public void setRegisterno(String registerno) {
        this.registerno = registerno;
    }

    public String getEntitytypecodeid() {
        return entitytypecodeid;
    }

    public void setEntitytypecodeid(String entitytypecodeid) {
        this.entitytypecodeid = entitytypecodeid;
    }

    public String getEntitytype() {
        return entitytype;
    }

    public void setEntitytype(String entitytype) {
        this.entitytype = entitytype;
    }

    public String getShoppingmode() {
        return shoppingmode;
    }

    public void setShoppingmode(String shoppingmode) {
        this.shoppingmode = shoppingmode;
    }

    public String getServicetypecodeid() {
        return servicetypecodeid;
    }

    public void setServicetypecodeid(String servicetypecodeid) {
        this.servicetypecodeid = servicetypecodeid;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getComplaintstypecodeid() {
        return complaintstypecodeid;
    }

    public void setComplaintstypecodeid(String complaintstypecodeid) {
        this.complaintstypecodeid = complaintstypecodeid;
    }

    public String getComplaintstype() {
        return complaintstype;
    }

    public void setComplaintstype(String complaintstype) {
        this.complaintstype = complaintstype;
    }

    public String getContentrs() {
        return contentrs;
    }

    public void setContentrs(String contentrs) {
        this.contentrs = contentrs;
    }

    public Date getDisputedate() {
        return disputedate;
    }

    public void setDisputedate(Date disputedate) {
        this.disputedate = disputedate;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOthercontacts() {
        return othercontacts;
    }

    public void setOthercontacts(String othercontacts) {
        this.othercontacts = othercontacts;
    }

    public String getIndustryname() {
        return industryname;
    }

    public void setIndustryname(String industryname) {
        this.industryname = industryname;
    }

    public String getReportedcontact() {
        return reportedcontact;
    }

    public void setReportedcontact(String reportedcontact) {
        this.reportedcontact = reportedcontact;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getApprovalnumber() {
        return approvalnumber;
    }

    public void setApprovalnumber(String approvalnumber) {
        this.approvalnumber = approvalnumber;
    }

    public String getLotnumber() {
        return lotnumber;
    }

    public void setLotnumber(String lotnumber) {
        this.lotnumber = lotnumber;
    }

    public String getSalesenterprise() {
        return salesenterprise;
    }

    public void setSalesenterprise(String salesenterprise) {
        this.salesenterprise = salesenterprise;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public Date getAcceptdate() {
        return acceptdate;
    }

    public void setAcceptdate(Date acceptdate) {
        this.acceptdate = acceptdate;
    }

    public Date getAssignmentdate() {
        return assignmentdate;
    }

    public void setAssignmentdate(Date assignmentdate) {
        this.assignmentdate = assignmentdate;
    }

    public Date getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(Date feedbackdate) {
        this.feedbackdate = feedbackdate;
    }

    public String getTransfernumber() {
        return transfernumber;
    }

    public void setTransfernumber(String transfernumber) {
        this.transfernumber = transfernumber;
    }

    public String getIncommingtype() {
        return incommingtype;
    }

    public void setIncommingtype(String incommingtype) {
        this.incommingtype = incommingtype;
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress;
    }

    public String getSubordinateplate() {
        return subordinateplate;
    }

    public void setSubordinateplate(String subordinateplate) {
        this.subordinateplate = subordinateplate;
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

    public String getDealcompany() {
        return dealcompany;
    }

    public void setDealcompany(String dealcompany) {
        this.dealcompany = dealcompany;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public String getShoppingplatform() {
        return shoppingplatform;
    }

    public void setShoppingplatform(String shoppingplatform) {
        this.shoppingplatform = shoppingplatform;
    }

    public String getAcceptancenumber() {
        return acceptancenumber;
    }

    public void setAcceptancenumber(String acceptancenumber) {
        this.acceptancenumber = acceptancenumber;
    }

    public String getTransferbooknumber() {
        return transferbooknumber;
    }

    public void setTransferbooknumber(String transferbooknumber) {
        this.transferbooknumber = transferbooknumber;
    }

    public List<Map<String, Opinion>> getOpinion() {
        return opinion;
    }

    public void setOpinion(List<Map<String, Opinion>> opinion) {
        this.opinion = opinion;
    }

    public String getAccsce() {
        return accsce;
    }

    public void setAccsce(String accsce) {
        this.accsce = accsce;
    }

    public Date getFeedbacktime() {
        return feedbacktime;
    }

    public void setFeedbacktime(Date feedbacktime) {
        this.feedbacktime = feedbacktime;
    }

    public Date getTodealwithtime() {
        return todealwithtime;
    }

    public void setTodealwithtime(Date todealwithtime) {
        this.todealwithtime = todealwithtime;
    }

    public String getIndustrynamecodeid() {
        return industrynamecodeid;
    }

    public void setIndustrynamecodeid(String industrynamecodeid) {
        this.industrynamecodeid = industrynamecodeid;
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
        return "Complaintform{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", tserialnum='" + tserialnum + '\'' +
                ", formtype='" + formtype + '\'' +
                ", rqsttitle='" + rqsttitle + '\'' +
                ", rqstperson='" + rqstperson + '\'' +
                ", gender='" + gender + '\'' +
                ", rqstnumber='" + rqstnumber + '\'' +
                ", linknumber='" + linknumber + '\'' +
                ", usertype='" + usertype + '\'' +
                ", answertype='" + answertype + '\'' +
                ", providetype='" + providetype + '\'' +
                ", providetypecodeid='" + providetypecodeid + '\'' +
                ", providelb='" + providelb + '\'' +
                ", focus='" + focus + '\'' +
                ", region='" + region + '\'' +
                ", age='" + age + '\'' +
                ", nation='" + nation + '\'' +
                ", status='" + status + '\'' +
                ", complaintpaper='" + complaintpaper + '\'' +
                ", disabilityidentification='" + disabilityidentification + '\'' +
                ", certificate='" + certificate + '\'' +
                ", codepostal='" + codepostal + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", career='" + career + '\'' +
                ", company='" + company + '\'' +
                ", subjectname='" + subjectname + '\'' +
                ", linktype='" + linktype + '\'' +
                ", subjectphone='" + subjectphone + '\'' +
                ", aicstationid='" + aicstationid + '\'' +
                ", postcode='" + postcode + '\'' +
                ", jyareacode='" + jyareacode + '\'' +
                ", subjectaddress='" + subjectaddress + '\'' +
                ", zcareacode='" + zcareacode + '\'' +
                ", subjectzcadd='" + subjectzcadd + '\'' +
                ", cksubjectname='" + cksubjectname + '\'' +
                ", ckjyareacode='" + ckjyareacode + '\'' +
                ", cksubjectaddress='" + cksubjectaddress + '\'' +
                ", objname='" + objname + '\'' +
                ", brand='" + brand + '\'' +
                ", objclassify='" + objclassify + '\'' +
                ", objclassifycodeid='" + objclassifycodeid + '\'' +
                ", objmoney=" + objmoney +
                ", producecompany='" + producecompany + '\'' +
                ", isweb='" + isweb + '\'' +
                ", importmark='" + importmark + '\'' +
                ", typespecification='" + typespecification + '\'' +
                ", quantity='" + quantity + '\'' +
                ", measureunit='" + measureunit + '\'' +
                ", price=" + price +
                ", purchasingdate=" + purchasingdate +
                ", problemtype='" + problemtype + '\'' +
                ", problemdescribe='" + problemdescribe + '\'' +
                ", keyword='" + keyword + '\'' +
                ", govlabeloutput='" + govlabeloutput + '\'' +
                ", differserv='" + differserv + '\'' +
                ", briefing='" + briefing + '\'' +
                ", anestimate=" + anestimate +
                ", economicloss=" + economicloss +
                ", tortclassify='" + tortclassify + '\'' +
                ", personalinjury='" + personalinjury + '\'' +
                ", injurydegree='" + injurydegree + '\'' +
                ", injuries='" + injuries + '\'' +
                ", consumptionsafety='" + consumptionsafety + '\'' +
                ", consumptionsafetytype='" + consumptionsafetytype + '\'' +
                ", even='" + even + '\'' +
                ", reduction='" + reduction + '\'' +
                ", report='" + report + '\'' +
                ", isoutmall='" + isoutmall + '\'' +
                ", attachfilename='" + attachfilename + '\'' +
                ", appealsource='" + appealsource + '\'' +
                ", informationopen='" + informationopen + '\'' +
                ", businesstypecodeid='" + businesstypecodeid + '\'' +
                ", businesstype='" + businesstype + '\'' +
                ", eventlevel='" + eventlevel + '\'' +
                ", updatedate=" + updatedate +
                ", rqstaddress='" + rqstaddress + '\'' +
                ", registrationnumber='" + registrationnumber + '\'' +
                ", recorddate=" + recorddate +
                ", receivemode='" + receivemode + '\'' +
                ", linkaddress='" + linkaddress + '\'' +
                ", emaiaddress='" + emaiaddress + '\'' +
                ", registerno='" + registerno + '\'' +
                ", entitytypecodeid='" + entitytypecodeid + '\'' +
                ", entitytype='" + entitytype + '\'' +
                ", shoppingmode='" + shoppingmode + '\'' +
                ", servicetypecodeid='" + servicetypecodeid + '\'' +
                ", servicetype='" + servicetype + '\'' +
                ", complaintstypecodeid='" + complaintstypecodeid + '\'' +
                ", complaintstype='" + complaintstype + '\'' +
                ", contentrs='" + contentrs + '\'' +
                ", disputedate=" + disputedate +
                ", ordernumber='" + ordernumber + '\'' +
                ", nationality='" + nationality + '\'' +
                ", othercontacts='" + othercontacts + '\'' +
                ", industryname='" + industryname + '\'' +
                ", industrynamecodeid='" + industrynamecodeid + '\'' +
                ", reportedcontact='" + reportedcontact + '\'' +
                ", productname='" + productname + '\'' +
                ", approvalnumber='" + approvalnumber + '\'' +
                ", lotnumber='" + lotnumber + '\'' +
                ", salesenterprise='" + salesenterprise + '\'' +
                ", expirationdate=" + expirationdate +
                ", acceptdate=" + acceptdate +
                ", assignmentdate=" + assignmentdate +
                ", feedbackdate=" + feedbackdate +
                ", transfernumber='" + transfernumber + '\'' +
                ", incommingtype='" + incommingtype + '\'' +
                ", detailaddress='" + detailaddress + '\'' +
                ", subordinateplate='" + subordinateplate + '\'' +
                ", incomingcommpany='" + incomingcommpany + '\'' +
                ", isprofessional='" + isprofessional + '\'' +
                ", dealcompany='" + dealcompany + '\'' +
                ", filed='" + filed + '\'' +
                ", shoppingplatform='" + shoppingplatform + '\'' +
                ", acceptancenumber='" + acceptancenumber + '\'' +
                ", transferbooknumber='" + transferbooknumber + '\'' +
                ", opinion=" + opinion +
                ", accsce='" + accsce + '\'' +
                ", feedbacktime=" + feedbacktime +
                ", todealwithtime=" + todealwithtime +
                ", registration='" + registration + '\'' +
                ", undertake='" + undertake + '\'' +
                ", satisfied='" + satisfied + '\'' +
                ", businesstypenew='" + businesstypenew + '\'' +
                ", businesstypecodeidnew='" + businesstypecodeidnew + '\'' +
                '}';
    }
}
