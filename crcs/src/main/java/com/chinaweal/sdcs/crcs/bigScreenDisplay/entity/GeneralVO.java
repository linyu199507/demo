package com.chinaweal.sdcs.crcs.bigScreenDisplay.entity;


public class GeneralVO {

    /**
     * 地区
     */
    private String region;

    /**
     * 类别
     */
    private String type;

    /**
     * 投诉类数值
     */
    private Integer comtotal;

    /**
     * 举报类数值
     */
    private Integer economytotal;

    /**
     * 投诉举报类总数
     */
    private Integer alltotal;

    /**
     * 办结总数
     */
    private Integer endtotal;

    public Integer getEndtotal() {
        return endtotal;
    }

    public void setEndtotal(Integer endtotal) {
        this.endtotal = endtotal;
    }

    public Integer getAlltotal() {
        return alltotal;
    }

    public void setAlltotal(Integer alltotal) {
        this.alltotal = alltotal;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getComtotal() {
        return comtotal;
    }

    public void setComtotal(Integer comtotal) {
        this.comtotal = comtotal;
    }

    public Integer getEconomytotal() {
        return economytotal;
    }

    public void setEconomytotal(Integer economytotal) {
        this.economytotal = economytotal;
    }

    @Override
    public String toString() {
        return "GeneralVO{" +
                "region='" + region + '\'' +
                ", type='" + type + '\'' +
                ", comtotal=" + comtotal +
                ", economytotal='" + economytotal + '\'' +
                ", alltotal='" + alltotal + '\'' +
                ", endtotal='" + endtotal + '\'' +
                '}';
    }
}
