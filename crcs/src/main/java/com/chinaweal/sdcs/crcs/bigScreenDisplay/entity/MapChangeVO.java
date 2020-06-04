package com.chinaweal.sdcs.crcs.bigScreenDisplay.entity;

/*领导视角-顺德地图*/

public class MapChangeVO {

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
    private String info;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
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
