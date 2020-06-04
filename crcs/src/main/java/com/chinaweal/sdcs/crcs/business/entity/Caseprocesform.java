package com.chinaweal.sdcs.crcs.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-08-28
 */
public class Caseprocesform implements Serializable {

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
     * 当前节点
     */
    private String nodeid;

    /**
     * 涉及主体
     */
    private String subjectname;

    /**
     * 主体类别
     */
    private String subjecttype;

    /**
     * 是否属实
     */
    private String isverified;

    /**
     * 属实意见
     */
    private String verifiedopinion;

    /**
     * 是否立案
     */
    private String isregister;

    /**
     * 是否销案
     */
    private String ispursuit;

    /**
     * 处理意见
     */
    private String handleopinion;

    /**
     * 更新时间
     */
    private Date updatedate;
    /**
     * 经营地址 2019-09-20
     */
    private String businessaddress;
    /**
     * 检查名称
     */
    private String semaknama;
    /**
     * 检查经营地址
     */
    private String semakbusinessaddress;
    /**
     * 行政调解书文号
     */
    private String mediationnumber;
    /**
     * 侵权类型
     */
    private String infringementtype;
    /**
     * 欺诈标志
     */
    private String infringingmarks;
    /**
     * 调解方式
     */
    private String mediationmethods;
    /**
     * 未履行义务
     */
    private String noobligation;
    /**
     * 调解结果
     */
    private String resultsofmediation;
    /**
     * 争议金额
     */
    private BigDecimal disputemoney;
    /**
     * 精神赔偿金额
     */
    private BigDecimal mentalmoney;
    /**
     * 加倍赔偿金额
     */
    private BigDecimal doublemoney;
    /**
     * 挽回经济损失
     */
    private BigDecimal financialloss;
    /**
     * 办结日期
     */
    private Date transferreddate;
    /**
     * 调解情况
     */
    private String mediationis;
    /**
     * 附件名称
     */
    private String attachfilename;

    /**
     * 涉及主体类别和类型codeid
     */
    private String subjecttypecodeid;


    /**
     * 侵权类型codeid
     */
    private String infringementtypecodeid;

    /**
     * 欺诈标志codeid
     */
    private String infringingmarkscodeid;

    /**
     * 未履行义务codeid
     */
    private String noobligationcodeid;

    /**
     * 调解方式codeid
     */
    private String mediationmethodscodeid;

    /**
     * 处理期限
     */
    private Integer limitdate;

    /**
     * 是否诉转案（1 是；0 否）
     */
    private String applealtocase;

    /**
     * 立案号
     */
    private String caseno;

    /**
     * 立案时间
     */
    private Date casedate;

    /**
     * 案由
     */
    private String casereason;

    /**
     * 是否无照经营
     */
    private String unlicensed;
    /**
     * 办理部门
     */
    private String handleDepart;

    /**
     * 经办人
     */
    private String operator;

    /**
     * 经办人意见
     */
    private String operatorOpinion;
    /**
     * 经办人电话
     */
    private String operatorPhone;
    /**
     * 是否部门领导审核；1=是、0=否
     */
    private String leadershipAudit;
    /**
     * 送审附言
     */
    private String postscript;
    /**
     * 销售地行政区划
     */
    private String saleDistrict;
    /**
     * 销售地
     */
    private String saleAddr;
    /**
     * 罚款金额；单位元
     */
    private BigDecimal penaltyAmount;
    /**
     * 没收金额；单位元
     */
    private BigDecimal confiscateAmount;
    /**
     * 赔偿方式
     */
    private String compensateMode;
    /**
     * 证据；1=发票、2=收据、3=收银小票、4=录音、5=录像、6=证人、7=无
     */
    private String evidence;
    /**
     * 核查分类
     */
    private String classification;

    /**
     * 商圈
     */
    private String businesscircle;



    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getSubjecttype() {
        return subjecttype;
    }

    public void setSubjecttype(String subjecttype) {
        this.subjecttype = subjecttype;
    }

    public String getIsverified() {
        return isverified;
    }

    public void setIsverified(String isverified) {
        this.isverified = isverified;
    }

    public String getVerifiedopinion() {
        return verifiedopinion;
    }

    public void setVerifiedopinion(String verifiedopinion) {
        this.verifiedopinion = verifiedopinion;
    }

    public String getIsregister() {
        return isregister;
    }

    public void setIsregister(String isregister) {
        this.isregister = isregister;
    }

    public String getIspursuit() {
        return ispursuit;
    }

    public void setIspursuit(String ispursuit) {
        this.ispursuit = ispursuit;
    }

    public String getHandleopinion() {
        return handleopinion;
    }

    public void setHandleopinion(String handleopinion) {
        this.handleopinion = handleopinion;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getBusinessaddress() {
        return businessaddress;
    }

    public void setBusinessaddress(String businessaddress) {
        this.businessaddress = businessaddress;
    }

    public String getSemaknama() {
        return semaknama;
    }

    public void setSemaknama(String semaknama) {
        this.semaknama = semaknama;
    }

    public String getSemakbusinessaddress() {
        return semakbusinessaddress;
    }

    public void setSemakbusinessaddress(String semakbusinessaddress) {
        this.semakbusinessaddress = semakbusinessaddress;
    }

    public String getMediationnumber() {
        return mediationnumber;
    }

    public void setMediationnumber(String mediationnumber) {
        this.mediationnumber = mediationnumber;
    }

    public String getInfringementtype() {
        return infringementtype;
    }

    public void setInfringementtype(String infringementtype) {
        this.infringementtype = infringementtype;
    }

    public String getInfringingmarks() {
        return infringingmarks;
    }

    public void setInfringingmarks(String infringingmarks) {
        this.infringingmarks = infringingmarks;
    }

    public String getMediationmethods() {
        return mediationmethods;
    }

    public void setMediationmethods(String mediationmethods) {
        this.mediationmethods = mediationmethods;
    }

    public String getNoobligation() {
        return noobligation;
    }

    public void setNoobligation(String noobligation) {
        this.noobligation = noobligation;
    }

    public String getResultsofmediation() {
        return resultsofmediation;
    }

    public void setResultsofmediation(String resultsofmediation) {
        this.resultsofmediation = resultsofmediation;
    }

    public BigDecimal getDisputemoney() {
        return disputemoney;
    }

    public void setDisputemoney(BigDecimal disputemoney) {
        this.disputemoney = disputemoney;
    }

    public BigDecimal getMentalmoney() {
        return mentalmoney;
    }

    public void setMentalmoney(BigDecimal mentalmoney) {
        this.mentalmoney = mentalmoney;
    }

    public BigDecimal getDoublemoney() {
        return doublemoney;
    }

    public void setDoublemoney(BigDecimal doublemoney) {
        this.doublemoney = doublemoney;
    }

    public BigDecimal getFinancialloss() {
        return financialloss;
    }

    public void setFinancialloss(BigDecimal financialloss) {
        this.financialloss = financialloss;
    }

    public Date getTransferreddate() {
        return transferreddate;
    }

    public void setTransferreddate(Date transferreddate) {
        this.transferreddate = transferreddate;
    }

    public String getMediationis() {
        return mediationis;
    }

    public void setMediationis(String mediationis) {
        this.mediationis = mediationis;
    }

    public String getAttachfilename() {
        return attachfilename;
    }

    public void setAttachfilename(String attachfilename) {
        this.attachfilename = attachfilename;
    }

    public String getSubjecttypecodeid() {
        return subjecttypecodeid;
    }

    public void setSubjecttypecodeid(String subjecttypecodeid) {
        this.subjecttypecodeid = subjecttypecodeid;
    }

    public String getInfringementtypecodeid() {
        return infringementtypecodeid;
    }

    public void setInfringementtypecodeid(String infringementtypecodeid) {
        this.infringementtypecodeid = infringementtypecodeid;
    }

    public String getInfringingmarkscodeid() {
        return infringingmarkscodeid;
    }

    public void setInfringingmarkscodeid(String infringingmarkscodeid) {
        this.infringingmarkscodeid = infringingmarkscodeid;
    }

    public String getNoobligationcodeid() {
        return noobligationcodeid;
    }

    public void setNoobligationcodeid(String noobligationcodeid) {
        this.noobligationcodeid = noobligationcodeid;
    }

    public String getMediationmethodscodeid() {
        return mediationmethodscodeid;
    }

    public void setMediationmethodscodeid(String mediationmethodscodeid) {
        this.mediationmethodscodeid = mediationmethodscodeid;
    }

    public Integer getLimitdate() {
        return limitdate;
    }

    public void setLimitdate(Integer limitdate) {
        this.limitdate = limitdate;
    }

    public String getApplealtocase() {
        return applealtocase;
    }

    public void setApplealtocase(String applealtocase) {
        this.applealtocase = applealtocase;
    }

    public String getCaseno() {
        return caseno;
    }

    public void setCaseno(String caseno) {
        this.caseno = caseno;
    }

    public Date getCasedate() {
        return casedate;
    }

    public void setCasedate(Date casedate) {
        this.casedate = casedate;
    }

    public String getCasereason() {
        return casereason;
    }

    public void setCasereason(String casereason) {
        this.casereason = casereason;
    }

    public String getUnlicensed() {
        return unlicensed;
    }

    public void setUnlicensed(String unlicensed) {
        this.unlicensed = unlicensed;
    }

    public String getHandleDepart() {
        return handleDepart;
    }

    public void setHandleDepart(String handleDepart) {
        this.handleDepart = handleDepart;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorOpinion() {
        return operatorOpinion;
    }

    public void setOperatorOpinion(String operatorOpinion) {
        this.operatorOpinion = operatorOpinion;
    }

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
    }

    public String getLeadershipAudit() {
        return leadershipAudit;
    }

    public void setLeadershipAudit(String leadershipAudit) {
        this.leadershipAudit = leadershipAudit;
    }

    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public String getSaleDistrict() {
        return saleDistrict;
    }

    public void setSaleDistrict(String saleDistrict) {
        this.saleDistrict = saleDistrict;
    }

    public String getSaleAddr() {
        return saleAddr;
    }

    public void setSaleAddr(String saleAddr) {
        this.saleAddr = saleAddr;
    }

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public BigDecimal getConfiscateAmount() {
        return confiscateAmount;
    }

    public void setConfiscateAmount(BigDecimal confiscateAmount) {
        this.confiscateAmount = confiscateAmount;
    }

    public String getCompensateMode() {
        return compensateMode;
    }

    public void setCompensateMode(String compensateMode) {
        this.compensateMode = compensateMode;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getBusinesscircle() {
        return businesscircle;
    }

    public void setBusinesscircle(String businesscircle) {
        this.businesscircle = businesscircle;
    }

    @Override
    public String toString() {
        return "Caseprocesform{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", nodeid='" + nodeid + '\'' +
                ", subjectname='" + subjectname + '\'' +
                ", subjecttype='" + subjecttype + '\'' +
                ", isverified='" + isverified + '\'' +
                ", verifiedopinion='" + verifiedopinion + '\'' +
                ", isregister='" + isregister + '\'' +
                ", ispursuit='" + ispursuit + '\'' +
                ", handleopinion='" + handleopinion + '\'' +
                ", updatedate=" + updatedate +
                ", businessaddress='" + businessaddress + '\'' +
                ", semaknama='" + semaknama + '\'' +
                ", semakbusinessaddress='" + semakbusinessaddress + '\'' +
                ", mediationnumber='" + mediationnumber + '\'' +
                ", infringementtype='" + infringementtype + '\'' +
                ", infringingmarks='" + infringingmarks + '\'' +
                ", mediationmethods='" + mediationmethods + '\'' +
                ", noobligation='" + noobligation + '\'' +
                ", resultsofmediation='" + resultsofmediation + '\'' +
                ", disputemoney=" + disputemoney +
                ", mentalmoney=" + mentalmoney +
                ", doublemoney=" + doublemoney +
                ", financialloss=" + financialloss +
                ", transferreddate=" + transferreddate +
                ", mediationis='" + mediationis + '\'' +
                ", attachfilename='" + attachfilename + '\'' +
                ", subjecttypecodeid='" + subjecttypecodeid + '\'' +
                ", infringementtypecodeid='" + infringementtypecodeid + '\'' +
                ", infringingmarkscodeid='" + infringingmarkscodeid + '\'' +
                ", noobligationcodeid='" + noobligationcodeid + '\'' +
                ", mediationmethodscodeid='" + mediationmethodscodeid + '\'' +
                ", limitdate=" + limitdate +
                ", applealtocase='" + applealtocase + '\'' +
                ", caseno='" + caseno + '\'' +
                ", casedate=" + casedate +
                ", casereason='" + casereason + '\'' +
                ", unlicensed='" + unlicensed + '\'' +
                ", handleDepart='" + handleDepart + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorOpinion='" + operatorOpinion + '\'' +
                ", operatorPhone='" + operatorPhone + '\'' +
                ", leadershipAudit='" + leadershipAudit + '\'' +
                ", postscript='" + postscript + '\'' +
                ", saleDistrict='" + saleDistrict + '\'' +
                ", saleAddr='" + saleAddr + '\'' +
                ", penaltyAmount=" + penaltyAmount +
                ", confiscateAmount=" + confiscateAmount +
                ", compensateMode='" + compensateMode + '\'' +
                ", evidence='" + evidence + '\'' +
                ", classification='" + classification + '\'' +
                ", businesscircle='" + businesscircle + '\'' +
                '}';
    }
}
