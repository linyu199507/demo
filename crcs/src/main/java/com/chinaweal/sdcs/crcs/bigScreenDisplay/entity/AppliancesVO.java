package com.chinaweal.sdcs.crcs.bigScreenDisplay.entity;

/*领导视角-家用电器类投诉品类 TOP10*/

public class AppliancesVO {

    /**
     * 品类
     */
    private String x;

    /**
     * 值
     */
    private Integer y;

    /**
     * 值
     */
    private Integer z;


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

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "BrandVO{" +
                "x='" + x + '\'' +
                ", y=" + y +
                ", z=" + z +
                ", s=" + s +
                '}';
    }
}
