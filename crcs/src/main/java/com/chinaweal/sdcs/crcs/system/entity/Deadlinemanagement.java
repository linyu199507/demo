package com.chinaweal.sdcs.crcs.system.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-09-16
 */
public class Deadlinemanagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 业务分类
     */
    private String bustype;

    /**
     * 工单分类
     */
    private String level;

    /**
     * 时限类型
     */
    private String deadlinetype;

    /**
     * 初步意见时限
     */
    private BigDecimal interimreplytime;

    /**
     * 初步意见时限单位
     */
    private String interimreplyunit;

    /**
     * 是否受理时限
     */
    private BigDecimal accepttime;

    /**
     * 是否受理时限单位
     */
    private String acceptunit;

    /**
     * 处理结果/是否立案时限
     */
    private BigDecimal handletime;

    /**
     * 处理结果/是否立案时限单位
     */
    private String handleunit;

    /**
     * 预警时间
     */
    private BigDecimal warningtime;

    /**
     * 预警时间单位
     */
    private String warningunit;

    /**
     * 更新时间
     */
    private Date updatedate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public String getDeadlinetype() {
        return deadlinetype;
    }

    public void setDeadlinetype(String deadlinetype) {
        this.deadlinetype = deadlinetype;
    }
    public BigDecimal getInterimreplytime() {
        return interimreplytime;
    }

    public void setInterimreplytime(BigDecimal interimreplytime) {
        this.interimreplytime = interimreplytime;
    }
    public String getInterimreplyunit() {
        return interimreplyunit;
    }

    public void setInterimreplyunit(String interimreplyunit) {
        this.interimreplyunit = interimreplyunit;
    }
    public BigDecimal getAccepttime() {
        return accepttime;
    }

    public void setAccepttime(BigDecimal accepttime) {
        this.accepttime = accepttime;
    }
    public String getAcceptunit() {
        return acceptunit;
    }

    public void setAcceptunit(String acceptunit) {
        this.acceptunit = acceptunit;
    }
    public BigDecimal getHandletime() {
        return handletime;
    }

    public void setHandletime(BigDecimal handletime) {
        this.handletime = handletime;
    }
    public String getHandleunit() {
        return handleunit;
    }

    public void setHandleunit(String handleunit) {
        this.handleunit = handleunit;
    }
    public BigDecimal getWarningtime() {
        return warningtime;
    }

    public void setWarningtime(BigDecimal warningtime) {
        this.warningtime = warningtime;
    }
    public String getWarningunit() {
        return warningunit;
    }

    public void setWarningunit(String warningunit) {
        this.warningunit = warningunit;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "Deadlinemanagement{" +
        "id=" + id +
        ", bustype=" + bustype +
        ", level=" + level +
        ", deadlinetype=" + deadlinetype +
        ", interimreplytime=" + interimreplytime +
        ", interimreplyunit=" + interimreplyunit +
        ", accepttime=" + accepttime +
        ", acceptunit=" + acceptunit +
        ", handletime=" + handletime +
        ", handleunit=" + handleunit +
        ", warningtime=" + warningtime +
        ", warningunit=" + warningunit +
        ", updatedate=" + updatedate +
        "}";
    }
}
