package com.poype.heracles.core.domain.model.dto;

/**
 * 应用简要信息，用于显示应用列表
 */
public class SimpleApplicationDto {
    /**
     * 应用Id
     */
    private String applicationId;

    /**
     * 应用所属域Id
     */
    private String domainId;

    /**
     * 应用名字
     */
    private String applicationName;

    /**
     * 应用类型
     */
    private String applicationType;

    /**
     * 所属子系统
     */
    private String belongSystem;

    /**
     * 所属业务
     */
    private String belongBusiness;

    @Override
    public String toString() {
        return "SimpleApplicationDto{" +
                "applicationId='" + applicationId + '\'' +
                ", domainId='" + domainId + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", applicationType='" + applicationType + '\'' +
                ", belongSystem='" + belongSystem + '\'' +
                ", belongBusiness='" + belongBusiness + '\'' +
                '}';
    }

    public SimpleApplicationDto() {
    }

    public SimpleApplicationDto(String applicationId, String domainId, String applicationName,
                                String applicationType, String belongSystem, String belongBusiness) {
        this.applicationId = applicationId;
        this.domainId = domainId;
        this.applicationName = applicationName;
        this.applicationType = applicationType;
        this.belongSystem = belongSystem;
        this.belongBusiness = belongBusiness;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
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
}
