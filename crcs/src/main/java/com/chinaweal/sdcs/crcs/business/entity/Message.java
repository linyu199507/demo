package com.chinaweal.sdcs.crcs.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * @param
 * @author mo sheng xin
 * @since 2020.01.06
 * 短信类
 **/
public class Message implements Serializable {
    @TableId("id")
    private String id;
    private String caseguid;
    private String formtype;//表单类型
    private String status; //发送状态：0成功，1失败
    private Date receivetime; //接收时间
    private String userid;  //接收者userid
    private String mobile; //手机号码
    private String messagetype;//短信类型：0新派，1预警
    private String unitname;  //部门名称
    private String name;    //姓名
    private String ename;   //账号
    private String serialnum; //表单号
    private Date sdexpirationdate; //到期时间
    private String content; //短信内容
    private String systemsource; //系统来源

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
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

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public Date getSdexpirationdate() {
        return sdexpirationdate;
    }

    public void setSdexpirationdate(Date sdexpirationdate) {
        this.sdexpirationdate = sdexpirationdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSystemsource() {
        return systemsource;
    }

    public void setSystemsource(String systemsource) {
        this.systemsource = systemsource;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", formtype='" + formtype + '\'' +
                ", status='" + status + '\'' +
                ", receivetime=" + receivetime +
                ", userid='" + userid + '\'' +
                ", mobile='" + mobile + '\'' +
                ", messagetype='" + messagetype + '\'' +
                ", unitname='" + unitname + '\'' +
                ", name='" + name + '\'' +
                ", ename='" + ename + '\'' +
                ", serialnum='" + serialnum + '\'' +
                ", sdexpirationdate=" + sdexpirationdate +
                ", content='" + content + '\'' +
                ", systemsource='" + systemsource + '\'' +
                '}';
    }
}
