package com.chinaweal.sdcs.crcs.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 维权转换请求记录
 * </p>
 *
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020-06-02
 */
@TableName(value = "rights_transform_request", resultMap = "BaseResultMap")
public class RightsTransformRequest extends Model<RightsTransformRequest> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 维权信息的id
     */
    private String rightsId;

    /**
     * 申请原因
     */
    private String reason;

    /**
     * 发往分局
     */
    private String branchOffice;

    /**
     * 审核状态（0审核中，1审核通过，-1审核不通过）
     */
    private Integer examineStatus;

    /**
     * 审核备注
     */
    private String examineRemark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人id（发起人）
     */
    private String creator;

    /**
     * 审核时间
     */
    private Date examineTime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 更新人id
     */
    private String updater;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRightsId() {
        return rightsId;
    }

    public void setRightsId(String rightsId) {
        this.rightsId = rightsId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(String branchOffice) {
        this.branchOffice = branchOffice;
    }

    public Integer getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(Integer examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getExamineRemark() {
        return examineRemark;
    }

    public void setExamineRemark(String examineRemark) {
        this.examineRemark = examineRemark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(Date examineTime) {
        this.examineTime = examineTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RightsTransformRequest{" +
        "id=" + id +
        ", rightsId=" + rightsId +
        ", reason=" + reason +
        ", branchOffice=" + branchOffice +
        ", examineStatus=" + examineStatus +
        ", examineRemark=" + examineRemark +
        ", createTime=" + createTime +
        ", creator=" + creator +
        ", examineTime=" + examineTime +
        ", updatetime=" + updatetime +
        ", updater=" + updater +
        "}";
    }
}
