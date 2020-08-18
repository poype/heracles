package com.poype.heracles.core.facade.request;

import com.poype.heracles.core.domain.model.dto.JavaApplicationDto;

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
     * 领域Id
     */
    private String domainId;

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
     * 代码仓库地址
     */
    private String gitUrl;

    /**
     * 研发人员
     */
    private List<String> devList;

    /**
     * 测试人员
     */
    private List<String> qaList;

    /**
     * Java应用信息
     */
    private JavaApplicationDto javaAppInfo;

    public AddApplicationRequest() {
    }

    public AddApplicationRequest(String appName, String appType, String domainId, String description, String devOwner,
                                 String qaOwner, String belongSystem, String belongBusiness, String gitUrl,
                                 List<String> devList, List<String> qaList, JavaApplicationDto javaAppInfo) {
        this.appName = appName;
        this.appType = appType;
        this.domainId = domainId;
        this.description = description;
        this.devOwner = devOwner;
        this.qaOwner = qaOwner;
        this.belongSystem = belongSystem;
        this.belongBusiness = belongBusiness;
        this.gitUrl = gitUrl;
        this.devList = devList;
        this.qaList = qaList;
        this.javaAppInfo = javaAppInfo;
    }

    @Override
    public String toString() {
        return "AddApplicationRequest{" +
                "appName='" + appName + '\'' +
                ", appType='" + appType + '\'' +
                ", domainId='" + domainId + '\'' +
                ", description='" + description + '\'' +
                ", devOwner='" + devOwner + '\'' +
                ", qaOwner='" + qaOwner + '\'' +
                ", belongSystem='" + belongSystem + '\'' +
                ", belongBusiness='" + belongBusiness + '\'' +
                ", gitUrl='" + gitUrl + '\'' +
                ", devList=" + devList +
                ", qaList=" + qaList +
                ", javaAppInfo=" + javaAppInfo +
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

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public JavaApplicationDto getJavaAppInfo() {
        return javaAppInfo;
    }

    public void setJavaAppInfo(JavaApplicationDto javaAppInfo) {
        this.javaAppInfo = javaAppInfo;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public List<String> getDevList() {
        return devList;
    }

    public void setDevList(List<String> devList) {
        this.devList = devList;
    }

    public List<String> getQaList() {
        return qaList;
    }

    public void setQaList(List<String> qaList) {
        this.qaList = qaList;
    }
}
