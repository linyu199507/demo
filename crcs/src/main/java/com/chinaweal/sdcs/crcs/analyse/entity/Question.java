package com.chinaweal.sdcs.crcs.analyse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chinaweal
 * @since 2020-01-19
 */
@TableName("analyse_question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId
    private String id;
    /**
     * 所属问题分类
     */
    private String question;

    /**
     * 具体问题分类codeId
     */
    private String specQuestion;

    /**
     * 标签
     */
    private String label;

    /**
     * 业务uid
     */
    private String caseguid;
    /**
     * 工单类型
     */
    private String formType;
    /**
     * 出现日期
     */
    private LocalDateTime dateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSpecQuestion() {
        return specQuestion;
    }

    public void setSpecQuestion(String specQuestion) {
        this.specQuestion = specQuestion;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCaseguid() {
        return caseguid;
    }

    public void setCaseguid(String caseguid) {
        this.caseguid = caseguid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", specQuestion='" + specQuestion + '\'' +
                ", label='" + label + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", formType='" + formType + '\'' +
                ", dateTime=" + dateTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
