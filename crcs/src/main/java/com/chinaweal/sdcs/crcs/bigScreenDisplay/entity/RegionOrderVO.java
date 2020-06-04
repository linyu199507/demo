package com.chinaweal.sdcs.crcs.bigScreenDisplay.entity;

/*领导视角-各地区投诉业务量*/

public class RegionOrderVO {

    /**
     * 序号
     */
    private Integer id;

    /**
     * 地区
     */
    private String pr;

    /**
     * 小图标地址
     */
    private String series1;

    /**
     * 办结率
     */
    private String series2;

    /**
     * 维权办结总数
     */
    private Integer attribute;

    /**
     * 立案总数
     */
    private String series3;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getSeries1() {
        return series1;
    }

    public void setSeries1(String series1) {
        this.series1 = series1;
    }

    public String getSeries2() {
        return series2;
    }

    public void setSeries2(String series2) {
        this.series2 = series2;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public String getSeries3() {
        return series3;
    }

    public void setSeries3(String series3) {
        this.series3 = series3;
    }

    @Override
    public String toString() {
        return "RegionOrderVO{" +
                "id=" + id +
                ", pr='" + pr + '\'' +
                ", series1='" + series1 + '\'' +
                ", series2='" + series2 + '\'' +
                ", attribute=" + attribute +
                ", series3='" + series3 + '\'' +
                '}';
    }
}
