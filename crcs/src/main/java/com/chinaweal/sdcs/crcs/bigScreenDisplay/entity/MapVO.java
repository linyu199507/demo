package com.chinaweal.sdcs.crcs.bigScreenDisplay.entity;

/*领导视角-被投诉主体 TOP10*/

public class MapVO {

    /**
     * 固定纬度
     */
    private Double lng;

    /**
     * 固定纬度
     */
    private Double lat;

    /**
     * 数值
     */
    private Integer info;

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getInfo() {
        return info;
    }

    public void setInfo(Integer info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "MapVO{" +
                "lng=" + lng +
                ", lat=" + lat +
                ", info=" + info +
                '}';
    }
}
