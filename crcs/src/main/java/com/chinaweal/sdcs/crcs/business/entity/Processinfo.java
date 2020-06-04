package com.chinaweal.sdcs.crcs.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-08-18
 * 流程信息表
 */
public class Processinfo implements Serializable {

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
     * 步骤id
     */
    private String nodeid;

    /**
     * 步骤
     */
    private String nodename;

    /**
     * 处理人
     */
    private String handler;

    /**
     * 处理人id
     */
    private String handleruserid;

    /**
     * 处理部门
     */
    private String handledept;

    /**
     * 处理部门id
     */
    private String handledeptid;

    /**
     * 流程状态
     */
    private String status;

    /**
     * 开始时间
     */
    private Date startdate;

    /**
     * 结束时间
     */
    private Date enddate;

    /**
     * 上一步节点id
     */
    private String fromnodeid;

    /**
     * 是否主要处理部门(1为处理，0为查看)
     */
    private String ismain;

    /**
     * 更新时间
     */
    private Date updatedate;

    /**
     * 处理机关id
     */
    private String handleorgid;

    /**
     * 处理机关名称
     */
    private String handleorgname;

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

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }
    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }
    public String getHandleruserid() {
        return handleruserid;
    }

    public void setHandleruserid(String handleruserid) {
        this.handleruserid = handleruserid;
    }
    public String getHandledept() {
        return handledept;
    }

    public void setHandledept(String handledept) {
        this.handledept = handledept;
    }
    public String getHandledeptid() {
        return handledeptid;
    }

    public void setHandledeptid(String handledeptid) {
        this.handledeptid = handledeptid;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getFromnodeid() {
        return fromnodeid;
    }

    public void setFromnodeid(String fromnodeid) {
        this.fromnodeid = fromnodeid;
    }

    public String getIsmain() {
        return ismain;
    }

    public void setIsmain(String ismain) {
        this.ismain = ismain;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getHandleorgid() {
        return handleorgid;
    }

    public void setHandleorgid(String handleorgid) {
        this.handleorgid = handleorgid;
    }

    public String getHandleorgname() {
        return handleorgname;
    }

    public void setHandleorgname(String handleorgname) {
        this.handleorgname = handleorgname;
    }

    @Override
    public String toString() {
        return "Processinfo{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", nodeid='" + nodeid + '\'' +
                ", nodename='" + nodename + '\'' +
                ", handler='" + handler + '\'' +
                ", handleruserid='" + handleruserid + '\'' +
                ", handledept='" + handledept + '\'' +
                ", handledeptid='" + handledeptid + '\'' +
                ", status='" + status + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", fromnodeid='" + fromnodeid + '\'' +
                ", ismain='" + ismain + '\'' +
                ", updatedate=" + updatedate +
                ", handleorgid='" + handleorgid + '\'' +
                ", handleorgname='" + handleorgname + '\'' +
                '}';
    }
}
