package com.poype.heracles.core.facade.request;


import com.poype.heracles.core.domain.model.AppOfRelease;

import java.util.List;

/**
 * 发布单创建对象
 */
public class CreateReleaseOrderRequest {
    /**
     * 版本Id
     */
    private String sprintId;

    /**
     * 发布名称
     */
    private String releaseName;

    /**
     * 发布描述
     */
    private String description;

    /**
     * 应用列表，包含应用名和分支
     */
    private List<AppOfRelease> appList;

    /**
     * 环境名称
     */
    private String envName;

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public List<AppOfRelease> getAppList() {
        return appList;
    }

    public void setAppList(List<AppOfRelease> appList) {
        this.appList = appList;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
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
}
