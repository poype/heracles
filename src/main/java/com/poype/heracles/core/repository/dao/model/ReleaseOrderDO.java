package com.poype.heracles.core.repository.dao.model;

import java.util.Date;

public class ReleaseOrderDO {

    /**
     * 发布单Id
     */
    private String orderId;

    /**
     * 发布名称
     */
    private String releaseName;

    /**
     * 描述
     */
    private String description;

    /**
     * 发布环境
     */
    private String envName;

    /**
     * 整体发布状态
     */
    private int status;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 发布时间
     */
    private Date releaseDate;

    private Date createTime;

    private Date updateTime;

    public ReleaseOrderDO(String orderId, String releaseName, String description, String envName, int status,
                          String operator, Date releaseDate) {
        this.orderId = orderId;
        this.releaseName = releaseName;
        this.description = description;
        this.envName = envName;
        this.status = status;
        this.operator = operator;
        this.releaseDate = releaseDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
