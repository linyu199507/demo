package com.chinaweal.sdcs.crcs.crawler.ywcl.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chinaweal
 * @since 2019-10-28
 */
public class Ywclshuntlist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 登记编号
     */
    private String caseid;

    /**
     * 类型
     */
    private String infotype;

    /**
     * 登记时间
     */
    private Date regtime;

    /**
     * 提供方
     */
    private String providername;

    /**
     * 涉及主体
     */
    private String invopt;

    /**
     * 登记部门
     */
    private String regdeptname;

    /**
     * 登记人
     */
    private String accregpername;

    /**
     * 登记内容
     */
    private String applidique;

    /**
     * 处理部门
     */
    private String clbmname;

    /**
     * 业务编码
     */
    private String regno;

    /**
     * 后面的详细请求要用到的（要查询出来用的）
     */
    private String runid;

    /**
     * 列表类型
     */
    private String listtype;

    /**
     * 更新时间
     */
    private Date updatedate;

    /**
     * 分流类型
     */
    private String distwhiname;

    /**
     * 详情请求url
     */
    private String detailsurl;

    /**
     * 详情url转义后的参数
     */
    private String regidurl;

    /**
     * 业务是否已经办理：0为未办理，1为已办理
     */
    private String status;

    /**
     * 业务是否删除：0为有效，1为失效
     */
    private String state;

    /**
     * 催办次数
     */
    private String cwcs;

    /**
     * 督办次数
     */
    private String dubcs;

    /**
     * 初步排插期限
     */
    private Date firstblqx;

    /**
     * 办结期限
     */
    private Date blqx;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }
    public String getInfotype() {
        return infotype;
    }

    public void setInfotype(String infotype) {
        this.infotype = infotype;
    }
    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }
    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }
    public String getInvopt() {
        return invopt;
    }

    public void setInvopt(String invopt) {
        this.invopt = invopt;
    }
    public String getRegdeptname() {
        return regdeptname;
    }

    public void setRegdeptname(String regdeptname) {
        this.regdeptname = regdeptname;
    }
    public String getAccregpername() {
        return accregpername;
    }

    public void setAccregpername(String accregpername) {
        this.accregpername = accregpername;
    }
    public String getApplidique() {
        return applidique;
    }

    public void setApplidique(String applidique) {
        this.applidique = applidique;
    }
    public String getClbmname() {
        return clbmname;
    }

    public void setClbmname(String clbmname) {
        this.clbmname = clbmname;
    }
    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }
    public String getRunid() {
        return runid;
    }

    public void setRunid(String runid) {
        this.runid = runid;
    }
    public String getListtype() {
        return listtype;
    }

    public void setListtype(String listtype) {
        this.listtype = listtype;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
    public String getDistwhiname() {
        return distwhiname;
    }

    public void setDistwhiname(String distwhiname) {
        this.distwhiname = distwhiname;
    }
    public String getDetailsurl() {
        return detailsurl;
    }

    public void setDetailsurl(String detailsurl) {
        this.detailsurl = detailsurl;
    }
    public String getRegidurl() {
        return regidurl;
    }

    public void setRegidurl(String regidurl) {
        this.regidurl = regidurl;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCwcs() {
        return cwcs;
    }

    public void setCwcs(String cwcs) {
        this.cwcs = cwcs;
    }

    public String getDubcs() {
        return dubcs;
    }

    public void setDubcs(String dubcs) {
        this.dubcs = dubcs;
    }

    public Date getFirstblqx() {
        return firstblqx;
    }

    public void setFirstblqx(Date firstblqx) {
        this.firstblqx = firstblqx;
    }

    public Date getBlqx() {
        return blqx;
    }

    public void setBlqx(Date blqx) {
        this.blqx = blqx;
    }

    @Override
    public String toString() {
        return "Ywclshuntlist{" +
                "id='" + id + '\'' +
                ", caseid='" + caseid + '\'' +
                ", infotype='" + infotype + '\'' +
                ", regtime=" + regtime +
                ", providername='" + providername + '\'' +
                ", invopt='" + invopt + '\'' +
                ", regdeptname='" + regdeptname + '\'' +
                ", accregpername='" + accregpername + '\'' +
                ", applidique='" + applidique + '\'' +
                ", clbmname='" + clbmname + '\'' +
                ", regno='" + regno + '\'' +
                ", runid='" + runid + '\'' +
                ", listtype='" + listtype + '\'' +
                ", updatedate=" + updatedate +
                ", distwhiname='" + distwhiname + '\'' +
                ", detailsurl='" + detailsurl + '\'' +
                ", regidurl='" + regidurl + '\'' +
                ", status='" + status + '\'' +
                ", state='" + state + '\'' +
                ", cwcs='" + cwcs + '\'' +
                ", dubcs='" + dubcs + '\'' +
                ", firstblqx=" + firstblqx +
                ", blqx=" + blqx +
                '}';
    }
}
