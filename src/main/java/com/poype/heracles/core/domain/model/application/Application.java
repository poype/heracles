package com.poype.heracles.core.domain.model.application;

import com.poype.heracles.common.util.IdUtil;
import com.poype.heracles.core.domain.model.application.config.ApplicationConfig;
import com.poype.heracles.core.domain.model.enums.ApplicationType;

import java.util.List;
import java.util.Set;

/**
 * 应用
 */
public class Application {

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
    private ApplicationType applicationType;

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
     * 开发成员
     */
    private Set<String> devSet;

    /**
     * 测试owner
     */
    private String qaOwner;

    /**
     * 测试成员
     */
    private Set<String> qaSet;

    /**
     * 所属子系统
     */
    private String belongSystem;

    /**
     * 所属业务
     */
    private String belongBusiness;

    /**
     * 与应用相关的配置列表
     */
    private List<ApplicationConfig> configs;

    public Application() {
    }

    public Application(String domainId, String applicationName, ApplicationType applicationType, String description,
                       String codeRepository, String devOwner, Set<String> devSet, String qaOwner, Set<String> qaSet,
                       String belongSystem, String belongBusiness, List<ApplicationConfig> configs) {
        this.applicationId = IdUtil.generateBizId();
        this.domainId = domainId;
        this.applicationName = applicationName;
        this.applicationType = applicationType;
        this.description = description;
        this.codeRepository = codeRepository;
        this.devOwner = devOwner;
        this.qaOwner = qaOwner;
        this.belongSystem = belongSystem;
        this.belongBusiness = belongBusiness;
        this.configs = configs;
        this.devSet = devSet;
        this.qaSet = qaSet;
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

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
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

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public List<ApplicationConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<ApplicationConfig> configs) {
        this.configs = configs;
    }

    public Set<String> getDevSet() {
        return devSet;
    }

    public void setDevSet(Set<String> devSet) {
        this.devSet = devSet;
    }

    public Set<String> getQaSet() {
        return qaSet;
    }

    public void setQaSet(Set<String> qaSet) {
        this.qaSet = qaSet;
    }
}
