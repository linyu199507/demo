package com.chinaweal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * excel导入n级表单例子
 */
@TableName(resultMap = "baseFormLevelMap")
public class BaseFormLevel implements Serializable {

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
     * 上级Id
     */
    private String parentid;

    /**
     * 所属下级
     */
    @TableField(exist = false)
    private List<BaseFormLevel> baseFormLevels = new ArrayList<>();

    /**
     * 更新时间
     */
    private LocalDateTime updatedate;


    @Override
    public String toString() {
        return "BaseFormLevel{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", codeid='" + codeid + '\'' +
                ", comment='" + comment + '\'' +
                ", orglevel='" + orglevel + '\'' +
                ", parentid='" + parentid + '\'' +
                ", baseFormLevels=" + baseFormLevels +
                ", updatedate=" + updatedate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseFormLevel that = (BaseFormLevel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(codeid, that.codeid) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(orglevel, that.orglevel) &&
                Objects.equals(parentid, that.parentid) &&
                Objects.equals(baseFormLevels, that.baseFormLevels) &&
                Objects.equals(updatedate, that.updatedate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, codeid, comment, orglevel, parentid, baseFormLevels, updatedate);
    }

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

    public LocalDateTime getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(LocalDateTime updatedate) {
        this.updatedate = updatedate;
    }

    public String getOrglevel() {
        return orglevel;
    }

    public void setOrglevel(String orglevel) {
        this.orglevel = orglevel;
    }

    public List<BaseFormLevel> getBaseFormLevels() {
        return baseFormLevels;
    }

    public void setBaseFormLevels(List<BaseFormLevel> baseFormLevels) {
        this.baseFormLevels = baseFormLevels;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}
