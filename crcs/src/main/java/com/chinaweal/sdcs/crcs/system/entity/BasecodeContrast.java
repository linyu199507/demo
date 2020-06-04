package com.chinaweal.sdcs.crcs.system.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 基础数据，12345佛山与12315对应码表
 * @author chinaweal
 * @since 2019-11-28
 */
public class BasecodeContrast implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 类型
     */
    private String type;

    /**
     * 全国12315代码
     */
    private String countryCode;

    /**
     * 佛山12345代码
     */
    private String fsCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFsCode() {
        return fsCode;
    }

    public void setFsCode(String fsCode) {
        this.fsCode = fsCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasecodeContrast that = (BasecodeContrast) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(fsCode, that.fsCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, countryCode, fsCode);
    }

    @Override
    public String toString() {
        return "BasecodeContrast{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", fsCode='" + fsCode + '\'' +
                '}';
    }
}
