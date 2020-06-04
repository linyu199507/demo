package com.chinaweal.sdcs.crcs.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 * 处理意见表
 */
public class Opinion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 业务主键
     */
    private String caseguid;

    /**
     * 数量
     */
    @TableField(exist = false)
    private Integer size;

    /**
     * 步骤
     */
    private String nodename;

    /**
     * 处理意见
     */
    private String handleopinion;

    /**
     * 处理人
     */
    private String handler;

    /**
     * 处理部门
     */
    private String handledept;

    /**
     * 操作
     */
    private String operate;

    /**
     * 处理时间
     */
    private Date handletime;

    /**
     * 更新时间
     */
    private Date updatedate;

    /**
     * 状态（0为有效，1为无效）
     */
    private String status;

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
    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }
    public String getHandleopinion() {
        return handleopinion;
    }

    public void setHandleopinion(String handleopinion) {
        this.handleopinion = handleopinion;
    }
    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }
    public String getHandledept() {
        return handledept;
    }

    public void setHandledept(String handledept) {
        this.handledept = handledept;
    }
    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }
    public Date getHandletime() {
        return handletime;
    }

    public void setHandletime(Date handletime) {
        this.handletime = handletime;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Opinion{" +
        "id=" + id +
        ", caseguid=" + caseguid +
        ", nodename=" + nodename +
        ", handleopinion=" + handleopinion +
        ", handler=" + handler +
        ", handledept=" + handledept +
        ", operate=" + operate +
        ", handletime=" + handletime +
        ", size=" + size +
        ", updatedate=" + updatedate +
        ", status="+ status +
        "}";
    }
}
