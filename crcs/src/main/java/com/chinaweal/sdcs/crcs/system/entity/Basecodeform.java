package com.chinaweal.sdcs.crcs.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chinaweal
 * @since 2019-09-29
 */
@JsonIgnoreProperties(value = { "handler" })
@TableName(resultMap = "BaseResultMap")
public class Basecodeform implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 类型
     */
    private String type;

    /**
     * 基础代码id(英文字段)
     */
    private String codeid;

    /**
     * 基础代码值(中文字段)
     */
    private String comment;

    /**
     * 阶级
     */
    private String orglevel;

    /**
     * 上一级主键
     */
    private String parentid;

    /**
     * 更新时间
     */
    private Date updatedate;

    /**
     * 回填codeid
     */
    private String flcode;

    /**
     * 所属下级
     */
    @TableField(exist = false)
    private List<Basecodeform> basecodeforms = new ArrayList<>();

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

    public String getCodeid() {
        return codeid;
    }

    public void setCodeid(String codeid) {
        this.codeid = codeid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getOrglevel() {
        return orglevel;
    }

    public void setOrglevel(String orglevel) {
        this.orglevel = orglevel;
    }

    public List<Basecodeform> getBasecodeforms() {
        return basecodeforms;
    }

    public void setBasecodeforms(List<Basecodeform> basecodeforms) {
        this.basecodeforms = basecodeforms;
    }

    public String getFlcode() {
        return flcode;
    }

    public void setFlcode(String flcode) {
        this.flcode = flcode;
    }

    @Override
    public String toString() {
        return "Basecodeform{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", codeid='" + codeid + '\'' +
                ", comment='" + comment + '\'' +
                ", parentid='" + parentid + '\'' +
                ", orglevel='" + orglevel + '\'' +
                ", updatedate='" + updatedate + '\'' +
                ", basecodeforms='" + basecodeforms + '\'' +
                ", flcode='" + flcode + '\'' +
                '}';
    }
}
