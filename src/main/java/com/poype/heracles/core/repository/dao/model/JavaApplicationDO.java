package com.poype.heracles.core.repository.dao.model;

import java.util.Date;

public class JavaApplicationDO {

    /**
     * 应用Id
     */
    private String applicationId;

    /**
     * 基准代码分支，很多项目代码不merge到master分支
     */
    private String baseCodeBranch;

    /**
     * 应用配置文件路径
     */
    private String configFilePath;

    /**
     * Jar路径
     */
    private String jarPath;

    /**
     * pom文件路径
     */
    private String pomPath;

    /**
     * 打包命令
     */
    private String mvnCommand;

    private Date createTime;

    private Date updateTime;

    public String getBaseCodeBranch() {
        return baseCodeBranch;
    }

    public void setBaseCodeBranch(String baseCodeBranch) {
        this.baseCodeBranch = baseCodeBranch;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }

    public String getPomPath() {
        return pomPath;
    }

    public void setPomPath(String pomPath) {
        this.pomPath = pomPath;
    }

    public String getMvnCommand() {
        return mvnCommand;
    }

    public void setMvnCommand(String mvnCommand) {
        this.mvnCommand = mvnCommand;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
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
