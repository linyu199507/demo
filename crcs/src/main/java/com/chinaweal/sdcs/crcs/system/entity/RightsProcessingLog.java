package com.chinaweal.sdcs.crcs.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 维权处理日志
 * </p>
 *
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020-06-02
 */
@TableName("rights_processing_log")
public class RightsProcessingLog extends Model<RightsProcessingLog> {

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
     * 处理结果
     */
    private String processing;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;


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

    public String getProcessing() {
        return processing;
    }

    public void setProcessing(String processing) {
        this.processing = processing;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RightsProcessingLog{" +
        "id=" + id +
        ", rightsId=" + rightsId +
        ", processing=" + processing +
        ", creator=" + creator +
        ", createTime=" + createTime +
        "}";
    }
}
