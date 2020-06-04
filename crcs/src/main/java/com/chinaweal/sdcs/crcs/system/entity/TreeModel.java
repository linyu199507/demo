package com.chinaweal.sdcs.crcs.system.entity;

import java.util.ArrayList;
import java.util.List;

public class TreeModel {
    private String id;      //主键
    private String codeid;   //节点ID
    private String text;    //显示文本
    private String pid;     //父级ID
    private String checked; //节点是否被选中
    private String state;   //节点状态 open or closed
    private String level;   //层级
    private List<TreeModel> children = new ArrayList<TreeModel>();  //子集

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeid() {
        return codeid;
    }

    public void setCodeid(String codeid) {
        this.codeid = codeid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<TreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<TreeModel> children) {
        this.children = children;
    }
}
