package com.chinaweal.sdcs.crcs.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 后台回填接口调用日志记录
 *
 * @author chinaweal
 * @since 2019-12-22
 */
public class BackfillCallLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @TableId
    private String uuid;

    /**
     * 请求地址
     */
    private String requestAddress;

    /**
     * 请求内容
     */
    private String body;

    /**
     * 入库时间
     */
    private LocalDateTime storageTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 响应内容
     */
    private String responseBody;
    /**
     * 单号
     */
    private String formNumber;

    /**
     * 调用步骤
     */
    private String step;

    /**
     * 回填失败标志 1为失败 0 为正常
     */
    private String failedMarks;


    /**
     * 回填失败工单修改状态时改变时间
     */
    private LocalDateTime total_changeTime;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRequestAddress() {
        return requestAddress;
    }

    public void setRequestAddress(String requestAddress) {
        this.requestAddress = requestAddress;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(LocalDateTime storageTime) {
        this.storageTime = storageTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getFailedMarks() {
        return failedMarks;
    }

    public void setFailedMarks(String failedMarks) {
        this.failedMarks = failedMarks;
    }

    public LocalDateTime getTotal_changeTime() {
        return total_changeTime;
    }

    public void setTotal_changeTime(LocalDateTime total_changeTime) {
        this.total_changeTime = total_changeTime;
    }

    @Override
    public String toString() {
        return "BackfillCallLog{" +
                "uuid='" + uuid + '\'' +
                ", requestAddress='" + requestAddress + '\'' +
                ", body='" + body + '\'' +
                ", storageTime=" + storageTime +
                ", updateTime=" + updateTime +
                ", responseBody='" + responseBody + '\'' +
                ", on='" + formNumber + '\'' +
                '}';
    }
}
