package com.poype.heracles.core.facade.result;

import java.util.List;

public class ReleaseOrderView {

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
    private String releaseDate;

    /**
     * 发布项列表
     */
    private List<ReleaseItemView> itemList;

    public ReleaseOrderView() {
    }

    public ReleaseOrderView(String orderId, String releaseName, String description, String envName,
                            String status, String operator, String releaseDate) {
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<ReleaseItemView> getItemList() {
        return itemList;
    }

    public void setItemList(List<ReleaseItemView> itemList) {
        this.itemList = itemList;
    }
}
