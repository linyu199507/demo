package com.chinaweal.sdcs.crcs.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * @param
 * @author mo sheng xin
 * @since
 **/
public class UsersApp implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId("userid")
    private String userid;
    private String gender;
    private String phonenumber;
    private String mobile;
    private String email;
    private String address;
    private String workno;
    private String worktype;
    private Date birthday;
    private Date hiredate;
    private Date leavedate;
    private String identityno;
    private String remark;
    private Date sdexpirationdate;
    private String serialnum;
    private String rqsttitle;
    private String caseguid;
    private String bustype;
    private String systemsource;
    private String mark;

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public String getCaseguid() {
        return caseguid;
    }

    public void setCaseguid(String caseguid) {
        this.caseguid = caseguid;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userId) {
        userid = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkno() {
        return workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public String getIdentityno() {
        return identityno;
    }

    public Date getSdexpirationdate() {
        return sdexpirationdate;
    }

    public void setSdexpirationdate(Date sdexpirationdate) {
        this.sdexpirationdate = sdexpirationdate;
    }

    public void setIdentityno(String identityno) {
        this.identityno = identityno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getSystemsource() {
        return systemsource;
    }

    public void setSystemsource(String systemsource) {
        this.systemsource = systemsource;
    }

    public String getMark(){return mark;}

    public void setMark(String mark){this.mark=mark;}

    @Override
    public String toString() {
        return "UsersApp{" +
                "userid='" + userid + '\'' +
                ", gender='" + gender + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", workno='" + workno + '\'' +
                ", worktype='" + worktype + '\'' +
                ", birthday=" + birthday +
                ", hiredate=" + hiredate +
                ", leavedate=" + leavedate +
                ", identityno='" + identityno + '\'' +
                ", remark='" + remark + '\'' +
                ", sdexpirationdate=" + sdexpirationdate +
                ", serialnum='" + serialnum + '\'' +
                ", rqsttitle='" + rqsttitle + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", bustype='" + bustype + '\'' +
                ", systemsource='" + systemsource + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
