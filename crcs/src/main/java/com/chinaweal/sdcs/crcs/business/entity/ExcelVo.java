package com.chinaweal.sdcs.crcs.business.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;

/**
 * 综合查询结果详情列表
 *
 * @author ：Huang Xiao
 * @version : 1.0
 * @since ： 2019/8/20
 */
@ExcelTarget(value = "ExcelVo")
public class ExcelVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Excel(name = "工单号", orderNum = "2")
    private String id;

    /**
     * 业务主键
     */
    @Excel(name = "表单类型", orderNum = "1")
    private String caseguid;

    /**
     * 工单分类
     */
    @Excel(name = "序号", orderNum = "0")
    private String focus;

    public ExcelVo() {
        super();
    }

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

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }
}
