package com.chinaweal.sdcs.crcs.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chinaweal
 * @since 2019-09-29
 */
@JsonIgnoreProperties(value = { "handler" })
@TableName(resultMap = "BaseResultMap")
public class StatisticsDate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域
     */
    private String region;

    /**
     * 区域案件总数
     */
    private Integer regiontotal;

    /**
     * 消费投诉案件总数
     */
    private Integer complaint;

    /**
     * 消费举报案件总数
     */
    private Integer economy;

    /**
     * 消费咨询案件总数
     */
    private Integer commom;

    /**
     * 案件办结率
     */
    private String transferred ;

    /**
     * 案件调解率
     */
    private String mediation;

    /**
     * 诉转案总数
     */
    private Integer applealtocase;

    /**
     * 月份
     */
    private String monthstart;

    /**
     * 月份
     */
    private String monthend;


    /**
     * 月份对应数据量
     */
    private Integer monthsData;



    public Integer getMonthsData() {
        return monthsData;
    }

    public void setMonthsData(Integer monthsData) {
        this.monthsData = monthsData;
    }

    public Integer getApplealtocase() {
        return applealtocase;
    }

    public void setApplealtocase(Integer applealtocase) {
        this.applealtocase = applealtocase;
    }

    public String getTransferred() {
        return transferred;
    }

    public void setTransferred(String transferred) {
        this.transferred = transferred;
    }

    public String getMediation() {
        return mediation;
    }

    public void setMediation(String mediation) {
        this.mediation = mediation;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getRegiontotal() {
        return regiontotal;
    }

    public void setRegiontotal(Integer regiontotal) {
        this.regiontotal = regiontotal;
    }

    public Integer getComplaint() {
        return complaint;
    }

    public void setComplaint(Integer complaint) {
        this.complaint = complaint;
    }

    public Integer getEconomy() {
        return economy;
    }

    public void setEconomy(Integer economy) {
        this.economy = economy;
    }

    public Integer getCommom() {
        return commom;
    }

    public void setCommom(Integer commom) {
        this.commom = commom;
    }

    public String getMonthstart() {
        return monthstart;
    }

    public void setMonthstart(String monthstart) {
        this.monthstart = monthstart;
    }

    public String getMonthend() {
        return monthend;
    }

    public void setMonthend(String monthend) {
        this.monthend = monthend;
    }


    @Override
    public String toString() {
        return "StatisticsDate{" +
                "region='" + region + '\'' +
                ", regiontotal=" + regiontotal +
                ", complaint=" + complaint +
                ", economy=" + economy +
                ", commom=" + commom +
                ", transferred='" + transferred + '\'' +
                ", mediation='" + mediation + '\'' +
                ", applealtocase=" + applealtocase +
                ", monthstart='" + monthstart + '\'' +
                ", monthend='" + monthend + '\'' +
                ", monthsData=" + monthsData +
                '}';
    }
}
