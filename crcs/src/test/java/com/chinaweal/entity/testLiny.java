package com.chinaweal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * excel导入n级表单例子
 */
//@TableName(resultMap = "testLinyMap")
public class testLiny implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 类型
     */
    private String caseguid;

    /**
     * 单号
     */
    private String sernumber;

    /**
     * 系统
     */
    private String system;

    /**
     * 类型
     */
    private String type;

    /**
     * 时间
     */
    private Date update;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseguid() {
        return caseguid;
    }

    public void setCaseguid(String caseguid) {
        this.caseguid = caseguid;
    }

    public String getSernumber() {
        return sernumber;
    }

    public void setSernumber(String sernumber) {
        this.sernumber = sernumber;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "testLiny{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", sernumber='" + sernumber + '\'' +
                ", system='" + system + '\'' +
                ", type='" + type + '\'' +
                ", update=" + update +
                '}';
    }
}
