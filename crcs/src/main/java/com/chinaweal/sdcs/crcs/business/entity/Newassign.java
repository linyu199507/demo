package com.chinaweal.sdcs.crcs.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @param
 * @author mo sheng xin
 * @since
 **/
public class Newassign  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String caseguid;
    private String serialnum;
    private String rqsttitle;
    private String content;
    private Date createdate;
    private String iscp;
    private String state;
    private Date assigndate;
    private String nextnodeid;
    private String fromnodeid;
    private String bustype;
    private String systemsource;

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

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getRqsttitle() {
        return rqsttitle;
    }

    public void setRqsttitle(String rqsttitle) {
        this.rqsttitle = rqsttitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getIscp() {
        return iscp;
    }

    public void setIscp(String iscp) {
        this.iscp = iscp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getAssigndate() {
        return assigndate;
    }

    public void setAssigndate(Date assigndate) {
        this.assigndate = assigndate;
    }

    public String getNextnodeid() {
        return nextnodeid;
    }

    public void setNextnodeid(String nextnodeid) {
        this.nextnodeid = nextnodeid;
    }

    public String getFromnodeid() {
        return fromnodeid;
    }

    public void setFromnodeid(String fromnodeid) {
        this.fromnodeid = fromnodeid;
    }

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public void setSystemsource(String systemsource){
        this.systemsource=systemsource;
    }

    public String getSystemsource(){
        return systemsource;
    }

    @Override
    public String toString() {
        return "Newassign{" +
                "id='" + id + '\'' +
                ", caseguid='" + caseguid + '\'' +
                ", serialnum='" + serialnum + '\'' +
                ", rqsttitle='" + rqsttitle + '\'' +
                ", content='" + content + '\'' +
                ", createdate=" + createdate +
                ", iscp='" + iscp + '\'' +
                ", state='" + state + '\'' +
                ", assigndate='" + assigndate + '\'' +
                ", nextnodeid='" + nextnodeid + '\'' +
                ", fromnodeid='" + fromnodeid + '\'' +
                ", bustype='" + bustype + '\'' +
                ", systemsource='" + systemsource + '\'' +
                '}';
    }
}
