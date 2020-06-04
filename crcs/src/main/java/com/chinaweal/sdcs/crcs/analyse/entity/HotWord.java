package com.chinaweal.sdcs.crcs.analyse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author HuangXiao
 * @since 2020-01-14
 */
@TableName("analyse_hot_word")
public class HotWord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;
    /**
     * 词频关键字
     */
    private String keyword;
    /**
     * 类型
     */
    private String codeType;

    /**
     * 次数
     */
    private Long count;


    /**
     * 行政区
     */
    private String area;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 出现日期
     */
    private LocalDate date;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


    /**
     * 经济类codeid
     */
    private String objectcategorycodeid;

    /**
     * 投诉类codeid
     */
    private String objclassifycodeid;

    /**
     * 《现代汉语语料库加工规范——词语切分与词性标注》 词性标记
     */
    private String tagging;

    /**
     * 工单交办时间
     */
    private Date assigndate;

    /**
     * 类型描述
     */
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getTagging() {
        return tagging;
    }

    public void setTagging(String tagging) {
        this.tagging = tagging;
    }


    public String getObjectcategorycodeid() {
        return objectcategorycodeid;
    }

    public void setObjectcategorycodeid(String objectcategorycodeid) {
        this.objectcategorycodeid = objectcategorycodeid;
    }

    public String getObjclassifycodeid() {
        return objclassifycodeid;
    }

    public void setObjclassifycodeid(String objclassifycodeid) {
        this.objclassifycodeid = objclassifycodeid;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public Date getAssigndate() {
        return assigndate;
    }

    public void setAssigndate(Date assigndate) {
        this.assigndate = assigndate;
    }

    public String getComment(){return comment;}

    public void setComment(String comment){this.comment=comment;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotWord hotWord = (HotWord) o;
        return Objects.equals(id, hotWord.id) &&
                Objects.equals(keyword, hotWord.keyword) &&
                Objects.equals(codeType, hotWord.codeType) &&
                Objects.equals(count, hotWord.count) &&
                Objects.equals(area, hotWord.area) &&
                Objects.equals(brand, hotWord.brand) &&
                Objects.equals(date, hotWord.date) &&
                Objects.equals(createTime, hotWord.createTime) &&
                Objects.equals(updateTime, hotWord.updateTime) &&
                Objects.equals(objectcategorycodeid, hotWord.objectcategorycodeid) &&
                Objects.equals(objclassifycodeid, hotWord.objclassifycodeid) &&
                Objects.equals(tagging, hotWord.tagging)&&
                Objects.equals(assigndate,hotWord.getAssigndate())&&
                Objects.equals(comment,hotWord.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keyword, codeType, count, area, brand, date, createTime, updateTime, objectcategorycodeid, objclassifycodeid, tagging, assigndate,comment);
    }

    @Override
    public String toString() {
        return "HotWord{" +
                "keyword=" + keyword +
                ", count=" + count +
                ", area=" + area +
                ", brand=" + brand +
                ", date=" + date +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tagging=" + tagging +
                ", objectcategorycodeid="+objectcategorycodeid+
                ", objclassifycodeid="+objclassifycodeid+
                ", codetype="+codeType+
                ", assigndate="+assigndate+
                ", comment="+comment+
                "}";
    }
}
