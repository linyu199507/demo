package com.chinaweal.sdcs.crcs.bigScreenDisplay.entity;

/*领导视角-被投诉品牌 TOP10*/

public class BrandVO {

    /**
     * 品牌
     */
    private String x;

    /**
     * 值
     */
    private Integer y;

    /**
     * 系列
     */
    private Integer s;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "BrandVO{" +
                "x='" + x + '\'' +
                ", y=" + y +
                ", s=" + s +
                '}';
    }
}
