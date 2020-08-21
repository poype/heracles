package com.poype.heracles.core.domain.model.dto;

import java.util.Date;

public class SimpleReleaseOrderDto {
    /**
     * 发布单Id
     */
    private String orderId;

    /**
     * 发布名称
     */
    private String releaseName;

    /**
     * 发布描述
     */
    private String description;

    /**
     * 环境名称
     */
    private String envName;

    /**
     * 发布状态
     */
    private String status;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 发布时间
     */
    private Date releaseDate;

    public SimpleReleaseOrderDto() {
    }

    public SimpleReleaseOrderDto(String orderId, String releaseName, String description, String envName,
                                 String status, String operator, Date releaseDate) {
        this.orderId = orderId;
        this.releaseName = releaseName;
        this.description = description;
        this.envName = envName;
        this.status = status;
        this.operator = operator;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "SimpleReleaseOrderView{" +
                "orderId='" + orderId + '\'' +
                ", releaseName='" + releaseName + '\'' +
                ", description='" + description + '\'' +
                ", envName='" + envName + '\'' +
                ", status='" + status + '\'' +
                ", operator='" + operator + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}
