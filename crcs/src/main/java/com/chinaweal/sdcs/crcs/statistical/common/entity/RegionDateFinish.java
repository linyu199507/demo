package com.chinaweal.sdcs.crcs.statistical.common.entity;

/**
 * @Author: NaJim
 * @Date: 2019/9/30
 */
public class RegionDateFinish {

    private static final long serialVersionUID = 1L;

    /**
     * 区域名称
     */
    private String formname;

    /**
     * 月份
     */
    private Integer mouths;

    /**
     * 年份
     */
    private String years;

    /**
     * 次数统计
     */
    private Integer count;

    public RegionDateFinish(String formname, Integer count) {
        this.formname = formname;
        this.count = count;
    }

    public RegionDateFinish(String formname, Integer mouths, String years, Integer count) {
        this.formname = formname;
        this.mouths = mouths;
        this.years = years;
        this.count = count;
    }

    public RegionDateFinish() {
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    public Integer getMouths() {
        return mouths;
    }

    public void setMouths(Integer mouths) {
        this.mouths = mouths;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "RegionDateFinish{" +
                "formname='" + formname + '\'' +
                ", mouths=" + mouths +
                ", years='" + years + '\'' +
                ", count=" + count +
                '}';
    }
}