package com.chinaweal.sdcs.crcs.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-08-18
 * 话务信息
 */
public class Customerinfo implements Serializable {

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
     * 接听座席姓名
     */
    private String seatsname;

    /**
     * 登记时间
     */
    private Date seatsregistrationtime;

    /**
     * 性别
     */
    private String seatssex;

    /**
     * 语种
     */
    private String seatslanguage;

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
    public String getSeatsname() {
        return seatsname;
    }

    public void setSeatsname(String seatsname) {
        this.seatsname = seatsname;
    }
    public Date getSeatsregistrationtime() {
        return seatsregistrationtime;
    }

    public void setSeatsregistrationtime(Date seatsregistrationtime) {
        this.seatsregistrationtime = seatsregistrationtime;
    }
    public String getSeatssex() {
        return seatssex;
    }

    public void setSeatssex(String seatssex) {
        this.seatssex = seatssex;
    }
    public String getSeatslanguage() {
        return seatslanguage;
    }

    public void setSeatslanguage(String seatslanguage) {
        this.seatslanguage = seatslanguage;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "Customerinfo{" +
        "id=" + id +
        ", caseguid=" + caseguid +
        ", seatsname=" + seatsname +
        ", seatsregistrationtime=" + seatsregistrationtime +
        ", seatssex=" + seatssex +
        ", seatslanguage=" + seatslanguage +
        ", updatedate=" + updatedate +
        "}";
    }
}
