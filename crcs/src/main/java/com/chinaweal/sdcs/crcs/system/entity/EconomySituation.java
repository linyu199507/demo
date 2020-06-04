package com.chinaweal.sdcs.crcs.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Liny
 * @since 2020-02-05
 */
@JsonIgnoreProperties(value = { "handler" })
@TableName(resultMap = "BaseResultMap")
public class EconomySituation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地区
     */
    private String region;

    /**
     * 业务类型
     */
    private String bustype;

    /**
     * 受理举报总数
     */
    private Integer accepttotal;

    /**
     * 已处理数
     */
    private Integer endtotal;

    /**
     * 已立案数
     */
    private Integer recordtotal;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public Integer getAccepttotal() {
        return accepttotal;
    }

    public void setAccepttotal(Integer accepttotal) {
        this.accepttotal = accepttotal;
    }

    public Integer getEndtotal() {
        return endtotal;
    }

    public void setEndtotal(Integer endtotal) {
        this.endtotal = endtotal;
    }

    public Integer getRecordtotal() {
        return recordtotal;
    }

    public void setRecordtotal(Integer recordtotal) {
        this.recordtotal = recordtotal;
    }

    @Override
    public String toString() {
        return "EconomySituation{" +
                "region='" + region + '\'' +
                ", bustype='" + bustype + '\'' +
                ", accepttotal=" + accepttotal +
                ", endtotal=" + endtotal +
                ", recordtotal=" + recordtotal +
                '}';
    }
}
