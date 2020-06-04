package com.chinaweal.sdcs.crcs.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2020-05-26
 */
public class Rights implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 经营主体的全称
     */
    private String enterprise;

    /**
     * 经营主体类型
     */
    private String type;

    /**
     * 地址
     */
    private String address;

    /**
     * 法定代表人
     */
    private String representative;

    /**
     * 服务站负责人电话
     */
    private String phone;

    /**
     * 辖区工商所名称
     */
    private String region;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Rights{" +
                "id='" + id + '\'' +
                ", enterprise='" + enterprise + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", representative='" + representative + '\'' +
                ", phone='" + phone + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
