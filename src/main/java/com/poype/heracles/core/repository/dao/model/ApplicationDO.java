package com.poype.heracles.core.repository.dao.model;

import java.util.Date;

public class ApplicationDO {

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
    private int applicationType;

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
    private String devs;

    /**
     * 测试owner
     */
    private String qaOwner;

    /**
     * 测试成员
     */
    private String qas;

    /**
     * 所属子系统
     */
    private String belongSystem;

    /**
     * 所属业务
     */
    private String belongBusiness;

    private Date createTime;

    private Date updateTime;

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

    public int getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(int applicationType) {
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

    public String getDevs() {
        return devs;
    }

    public void setDevs(String devs) {
        this.devs = devs;
    }

    public String getQaOwner() {
        return qaOwner;
    }

    public void setQaOwner(String qaOwner) {
        this.qaOwner = qaOwner;
    }

    public String getQas() {
        return qas;
    }

    public void setQas(String qas) {
        this.qas = qas;
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
