package com.chinaweal.sdcs.crcs.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Liny
 * @since 2020-02-03
 */
@JsonIgnoreProperties(value = { "handler" })
@TableName(resultMap = "BaseResultMap")
public class OverallSituation implements Serializable {

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
     * 业务总量
     */
    private Integer bustotal;

    /**
     * 办结总量
     */
    private Integer endtotal;

    /**
     * 调节成功总量
     */
    private Integer successful;


    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public Integer getBustotal() {
        return bustotal;
    }

    public void setBustotal(Integer bustotal) {
        this.bustotal = bustotal;
    }

    public Integer getEndtotal() {
        return endtotal;
    }

    public void setEndtotal(Integer endtotal) {
        this.endtotal = endtotal;
    }

    public Integer getSuccessful() {
        return successful;
    }

    public void setSuccessful(Integer successful) {
        this.successful = successful;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "OverallSituation{" +
                "region='" + region + '\'' +
                "bustype='" + bustype + '\'' +
                "endtotal='" + endtotal + '\'' +
                "successful='" + successful + '\'' +
                ", bustotal=" + bustotal +
                '}';
    }
}
