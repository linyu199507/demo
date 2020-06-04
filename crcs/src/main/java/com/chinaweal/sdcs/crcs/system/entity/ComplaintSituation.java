package com.chinaweal.sdcs.crcs.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Liny
 * @since 2020-02-06
 */
@JsonIgnoreProperties(value = { "handler" })
@TableName(resultMap = "BaseResultMap")
public class ComplaintSituation implements Serializable {

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
     * 投诉总数
     */
    private Integer overalltotal;

    /**
     * 调解数
     */
    private Integer endtotal;

    /**
     * 诉转案数
     */
    private Integer toeconomytotal;

    /**
     * 挽回金额
     */
    private BigDecimal recovermoney;

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

    public Integer getOveralltotal() {
        return overalltotal;
    }

    public void setOveralltotal(Integer overalltotal) {
        this.overalltotal = overalltotal;
    }

    public Integer getEndtotal() {
        return endtotal;
    }

    public void setEndtotal(Integer endtotal) {
        this.endtotal = endtotal;
    }

    public Integer getToeconomytotal() {
        return toeconomytotal;
    }

    public void setToeconomytotal(Integer toeconomytotal) {
        this.toeconomytotal = toeconomytotal;
    }

    public BigDecimal getRecovermoney() {
        return recovermoney;
    }

    public void setRecovermoney(BigDecimal recovermoney) {
        this.recovermoney = recovermoney;
    }

    @Override
    public String toString() {
        return "ComplaintSituation{" +
                "region='" + region + '\'' +
                ", bustype='" + bustype + '\'' +
                ", overalltotal=" + overalltotal +
                ", endtotal=" + endtotal +
                ", toeconomytotal=" + toeconomytotal +
                ", recovermoney=" + recovermoney +
                '}';
    }
}
