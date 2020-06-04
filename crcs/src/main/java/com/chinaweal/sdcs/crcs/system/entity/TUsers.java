package com.chinaweal.sdcs.crcs.system.entity;

import java.io.Serializable;

/**
 * @author chinaweal
 * @since 2020-04-22
 */
public class TUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private String username;

    private String password;

    private String realname;

    private String locked;

    private String deleted;

    private String orgdeptid;

    private String orgunitid;

    private String orgjobid;

    private Integer userprop;

    private String userstatus;

    private Integer gradenum;

    private Integer customindex;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
    public String getOrgdeptid() {
        return orgdeptid;
    }

    public void setOrgdeptid(String orgdeptid) {
        this.orgdeptid = orgdeptid;
    }
    public String getOrgunitid() {
        return orgunitid;
    }

    public void setOrgunitid(String orgunitid) {
        this.orgunitid = orgunitid;
    }
    public String getOrgjobid() {
        return orgjobid;
    }

    public void setOrgjobid(String orgjobid) {
        this.orgjobid = orgjobid;
    }
    public Integer getUserprop() {
        return userprop;
    }

    public void setUserprop(Integer userprop) {
        this.userprop = userprop;
    }
    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }
    public Integer getGradenum() {
        return gradenum;
    }

    public void setGradenum(Integer gradenum) {
        this.gradenum = gradenum;
    }
    public Integer getCustomindex() {
        return customindex;
    }

    public void setCustomindex(Integer customindex) {
        this.customindex = customindex;
    }

    @Override
    public String toString() {
        return "TUsers{" +
        "userid=" + userid +
        ", username=" + username +
        ", password=" + password +
        ", realname=" + realname +
        ", locked=" + locked +
        ", deleted=" + deleted +
        ", orgdeptid=" + orgdeptid +
        ", orgunitid=" + orgunitid +
        ", orgjobid=" + orgjobid +
        ", userprop=" + userprop +
        ", userstatus=" + userstatus +
        ", gradenum=" + gradenum +
        ", customindex=" + customindex +
        "}";
    }
}
