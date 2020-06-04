package com.chinaweal.sdcs.crcs.system.entity;

import java.io.Serializable;

/**
 * @author chinaweal
 * @since 2020-04-21
 */
public class TOrgunits implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orgunitid;

    private String orgunitname;

    private String deleted;

    private String orgnumber;

    private String parentid;

    private String unittype;

    private Integer orglevel;

    public String getOrgunitid() {
        return orgunitid;
    }

    public void setOrgunitid(String orgunitid) {
        this.orgunitid = orgunitid;
    }
    public String getOrgunitname() {
        return orgunitname;
    }

    public void setOrgunitname(String orgunitname) {
        this.orgunitname = orgunitname;
    }
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
    public String getOrgnumber() {
        return orgnumber;
    }

    public void setOrgnumber(String orgnumber) {
        this.orgnumber = orgnumber;
    }
    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
    public String getUnittype() {
        return unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }
    public Integer getOrglevel() {
        return orglevel;
    }

    public void setOrglevel(Integer orglevel) {
        this.orglevel = orglevel;
    }

    @Override
    public String toString() {
        return "TOrgunits{" +
                "orgunitid='" + orgunitid + '\'' +
                ", orgunitname='" + orgunitname + '\'' +
                ", deleted='" + deleted + '\'' +
                ", orgnumber='" + orgnumber + '\'' +
                ", parentid='" + parentid + '\'' +
                ", unittype='" + unittype + '\'' +
                ", orglevel=" + orglevel +
                '}';
    }
}
