package com.poype.heracles.core.facade.request;

import java.util.List;

public class AddApplicationRequest {

    /**
     * 应用的名字
     */
    private String appName;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 开发负责人
     */
    private String devOwner;

    /**
     * 测试负责人
     */
    private String qaOwner;

    /**
     * 所属子系统
     */
    private String belongSystem;

    /**
     * 所属业务
     */
    private String belongBusiness;

    /**
     * 域名配置项名字
     */
    private List<String> hostConfigNames;

    /**
     * 代码仓库地址
     */
    private String gitUrl;

    @Override
    public String toString() {
        return "AddApplicationRequest{" +
                "appName='" + appName + '\'' +
                ", appType='" + appType + '\'' +
                ", description='" + description + '\'' +
                ", devOwner='" + devOwner + '\'' +
                ", qaOwner='" + qaOwner + '\'' +
                ", belongSystem='" + belongSystem + '\'' +
                ", belongBusiness='" + belongBusiness + '\'' +
                ", hostConfigNames=" + hostConfigNames +
                ", gitUrl='" + gitUrl + '\'' +
                '}';
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevOwner() {
        return devOwner;
    }

    public void setDevOwner(String devOwner) {
        this.devOwner = devOwner;
    }

    public String getQaOwner() {
        return qaOwner;
    }

    public void setQaOwner(String qaOwner) {
        this.qaOwner = qaOwner;
    }

    public String getBelongSystem() {
        return belongSystem;
    }

    public void setBelongSystem(String belongSystem) {
        this.belongSystem = belongSystem;
    }

    public String getBelongBusiness() {
        return belongBusiness;
    }

    public void setBelongBusiness(String belongBusiness) {
        this.belongBusiness = belongBusiness;
    }

    public List<String> getHostConfigNames() {
        return hostConfigNames;
    }

    public void setHostConfigNames(List<String> hostConfigNames) {
        this.hostConfigNames = hostConfigNames;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }
}
