package com.chinaweal.sdcs.crcs.crawler.backfill.entity;

import java.io.Serializable;
import java.util.Date;

public class BackfillOrder implements Serializable {
    /**
     * 主键
     */
    private String uuid;


    /**
     * 请求地址
     */
    private String request_address;


    /**
     * 入库时间
     */
    private Date storage_time;


    /**
     * 更新时间
     */
    private Date update_time;


    /**
     * 单号
     */
    private String form_number;


    /**
     * 调用步骤
     */
    private String step;


    /**
     * 回填失败标志 1为失败 0 为正常
     */
    private String failed_marks;


    /**
     * 回填失败标志 1为失败 0 为正常
     */
    private Integer total;

    /**
     * 回填失败工单修改状态时改变时间
     */
    private Date total_changeTime;

    /**
     * 业务人员确定标志 1为未确定 0 为确定
     */
    private String confirm;

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Date getTotal_changeTime() {
        return total_changeTime;
    }

    public void setTotal_changeTime(Date total_changeTime) {
        this.total_changeTime = total_changeTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    /**
     * 系统来源
     */
    private String system;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRequest_address() {
        return request_address;
    }

    public void setRequest_address(String request_address) {
        this.request_address = request_address;
    }

    public Date getStorage_time() {
        return storage_time;
    }

    public void setStorage_time(Date storage_time) {
        this.storage_time = storage_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getForm_number() {
        return form_number;
    }

    public void setForm_number(String form_number) {
        this.form_number = form_number;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getFailed_marks() {
        return failed_marks;
    }

    public void setFailed_marks(String failed_marks) {
        this.failed_marks = failed_marks;
    }

    @Override
    public String toString() {
        return "BackfillOrder{" +
                "uuid='" + uuid + '\'' +
                ", request_address='" + request_address + '\'' +
                ", storage_time=" + storage_time +
                ", update_time=" + update_time +
                ", form_number='" + form_number + '\'' +
                ", step='" + step + '\'' +
                ", failed_marks='" + failed_marks + '\'' +
                ", system='" + failed_marks + '\'' +
                ", total='" + total + '\'' +
                ", total_changeTime='" + total_changeTime + '\'' +
                ", confirm='" + confirm + '\'' +
                '}';
    }
}
