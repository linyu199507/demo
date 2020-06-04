package com.chinaweal.sdcs.crcs.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2020-05-28
 */
public class RightsDetails extends Model<RightsDetails> {

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
     * 所属辖区
     */
    private String region;

    /**
     * 经营主体
     */
    private String enterprise;

    /**
     * 登记时间
     */
    private Date registrationdate;

    /**
     * 登记人
     */
    private String staffname;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 商家名称
     */
    private String vendorname;
    /**
     * 投诉问题
     */
    private String problem;

    /**
     * 是否违法
     */
    private String isillegal;

    /**
     * 问题描述
     */
    private String rqstcontent;

    /**
     * 处理结果
     */
    private String processing;

    /**
     * 表单号
     */
    private String serialnum;

    /**
     * 标题
     */
    private String rqsttitle;

    /**
     * 投诉人地址
     */
    private String address;

    /**
     * 商家名称/被投诉人电话
     */
    private String vendorphone;

    /**
     * 商家名称/被投诉人地址
     */
    private String vendoraddress;

    /**
     * 商家名称/被投诉人所属区
     */
    private String vendorregion;

    /**
     * 商家名称/被投诉人所属镇
     */
    private String vendortown;

    /**
     * 商品名称
     */
    private String goodsname;

    /**
     * 商品型号
     */
    private String goodsmodel;

    /**
     * 投诉类型
     */
    private String goodstype;

    /**
     * 投诉性质
     */
    private String nature;

    /**
     * 数量
     */
    private String number;

    /**
     * 涉及金额
     */
    private BigDecimal money;

    /**
     * 购买时间
     */
    private Date shoppingdate;

    /**
     * 发生时间
     */
    private Date happendate;

    /**
     * 诉求内容
     */
    private String appeal;

    /**
     * 请求标志
     */
    private String requestmark;

    /**
     * 回馈标志
     */
    private String feedbackmark;

    /**
     * 用户标志
     */
    private String userid;

    /**
     * 请求内容/申请原因
     */
    private String requestcontent;

    /**
     * 处理结果请求时间
     */
    private Date processingdate;

    /**
     * 请求申请时间
     */
    private Date requestdate;



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public Date getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(Date registrationdate) {
        this.registrationdate = registrationdate;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getIsillegal() {
        return isillegal;
    }

    public void setIsillegal(String isillegal) {
        this.isillegal = isillegal;
    }

    public String getRqstcontent() {
        return rqstcontent;
    }

    public void setRqstcontent(String rqstcontent) {
        this.rqstcontent = rqstcontent;
    }

    public String getProcessing() {
        return processing;
    }

    public void setProcessing(String processing) {
        this.processing = processing;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getRqsttitle() {
        return rqsttitle;
    }

    public void setRqsttitle(String rqsttitle) {
        this.rqsttitle = rqsttitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVendorphone() {
        return vendorphone;
    }

    public void setVendorphone(String vendorphone) {
        this.vendorphone = vendorphone;
    }

    public String getVendoraddress() {
        return vendoraddress;
    }

    public void setVendoraddress(String vendoraddress) {
        this.vendoraddress = vendoraddress;
    }

    public String getVendorregion() {
        return vendorregion;
    }

    public void setVendorregion(String vendorregion) {
        this.vendorregion = vendorregion;
    }

    public String getVendortown() {
        return vendortown;
    }

    public void setVendortown(String vendortown) {
        this.vendortown = vendortown;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodsmodel() {
        return goodsmodel;
    }

    public void setGoodsmodel(String goodsmodel) {
        this.goodsmodel = goodsmodel;
    }

    public String getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(String goodstype) {
        this.goodstype = goodstype;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getShoppingdate() {
        return shoppingdate;
    }

    public void setShoppingdate(Date shoppingdate) {
        this.shoppingdate = shoppingdate;
    }

    public Date getHappendate() {
        return happendate;
    }

    public void setHappendate(Date happendate) {
        this.happendate = happendate;
    }

    public String getAppeal() {
        return appeal;
    }

    public void setAppeal(String appeal) {
        this.appeal = appeal;
    }

    public String getRequestmark() {
        return requestmark;
    }

    public void setRequestmark(String requestmark) {
        this.requestmark = requestmark;
    }

    public String getFeedbackmark() {
        return feedbackmark;
    }

    public void setFeedbackmark(String feedbackmark) {
        this.feedbackmark = feedbackmark;
    }

    public String getRequestcontent() {
        return requestcontent;
    }

    public void setRequestcontent(String requestcontent) {
        this.requestcontent = requestcontent;
    }

    public Date getProcessingdate() {
        return processingdate;
    }

    public void setProcessingdate(Date processingdate) {
        this.processingdate = processingdate;
    }

    public Date getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(Date requestdate) {
        this.requestdate = requestdate;
    }

    @Override
    public String toString() {
        return "RightsDetails{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", region='" + region + '\'' +
                ", enterprise='" + enterprise + '\'' +
                ", registrationdate=" + registrationdate +
                ", staffname='" + staffname + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", vendorname='" + vendorname + '\'' +
                ", problem='" + problem + '\'' +
                ", isillegal='" + isillegal + '\'' +
                ", rqstcontent='" + rqstcontent + '\'' +
                ", processing='" + processing + '\'' +
                ", serialnum='" + serialnum + '\'' +
                ", rqsttitle='" + rqsttitle + '\'' +
                ", address='" + address + '\'' +
                ", vendorphone='" + vendorphone + '\'' +
                ", vendoraddress='" + vendoraddress + '\'' +
                ", vendorregion='" + vendorregion + '\'' +
                ", vendortown='" + vendortown + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodsmodel='" + goodsmodel + '\'' +
                ", goodstype='" + goodstype + '\'' +
                ", nature='" + nature + '\'' +
                ", number='" + number + '\'' +
                ", money=" + money +
                ", shoppingdate=" + shoppingdate +
                ", happendate=" + happendate +
                ", appeal='" + appeal + '\'' +
                ", requestmark='" + requestmark + '\'' +
                ", feedbackmark='" + feedbackmark + '\'' +
                ", userid='" + userid + '\'' +
                ", requestcontent='" + requestcontent + '\'' +
                ", processingdate=" + processingdate +
                ", requestdate=" + requestdate +
                '}';
    }
}
