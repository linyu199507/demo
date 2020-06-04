package com.chinaweal.sdcs.crcs.analyse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * @author chinaweal
 * @since 2020-01-16
 */
@TableName("analyse_time_record")
public class TimeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录类型
     */
    @TableId
    private String recordId;

    /**
     * 最后的统计时间，也就是下次的开始统计时间（包含)
     */
    private LocalDateTime lastDate;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public LocalDateTime getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDateTime lastDate) {
        this.lastDate = lastDate;
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

    @Override
    public String toString() {
        return "TimeRecord{" +
                "recordId=" + recordId +
                ", lastDate=" + lastDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
