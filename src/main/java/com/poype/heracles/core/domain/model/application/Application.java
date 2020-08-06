package com.poype.heracles.core.domain.model.application;

import com.poype.heracles.common.util.IdUtil;

/**
 * 应用
 */
public class Application {

    /**
     * 应用Id
     */
    private String applicationId;

    /**
     * 应用名字
     */
    private String applicationName;

    /**
     * 应用类型
     */
    private String applicationType;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 代码仓库
     */
    private String codeRepository;

    /**
     * 研发owner
     */
    private String devOwner;

    /**
     * 测试owner
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

    public Application() {
    }

    public Application(String applicationName, String applicationType, String description, String codeRepository,
                       String devOwner, String qaOwner, String belongSystem, String belongBusiness) {
        this.applicationId = IdUtil.generateApplicationId();
        this.applicationName = applicationName;
        this.applicationType = applicationType;
        this.description = description;
        this.codeRepository = codeRepository;
        this.devOwner = devOwner;
        this.qaOwner = qaOwner;
        this.belongSystem = belongSystem;
        this.belongBusiness = belongBusiness;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodeRepository() {
        return codeRepository;
    }

    public void setCodeRepository(String codeRepository) {
        this.codeRepository = codeRepository;
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
}
