package com.chinaweal.sdcs.crcs.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author chinaweal
 * @since 2019-09-29
 */
@JsonIgnoreProperties(value = { "handler" })
@TableName(resultMap = "BaseResultMap")
public class TimesVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 起始
     */
    private String timestart;

    @Override
    public String toString() {
        return "TimesVo{" +
                "timestart='" + timestart + '\'' +
                ", timeend='" + timeend + '\'' +
                '}';
    }

    /**
     * 结束
     */
    private String timeend;

    public String getTimestart() {
        return timestart;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }

    public String getTimeend() {
        return timeend;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }
}
