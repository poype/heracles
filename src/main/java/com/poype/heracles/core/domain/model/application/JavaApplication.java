package com.poype.heracles.core.domain.model.application;

import com.poype.heracles.core.domain.model.application.config.ApplicationConfig;
import com.poype.heracles.core.domain.model.enums.ApplicationType;

import java.util.List;
import java.util.Set;

/**
 * Java类型应用
 */
public class JavaApplication extends Application {

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

    public JavaApplication(String domainId, String applicationName, ApplicationType applicationType,
                           String description, String codeRepository, String devOwner, Set<String> devSet,
                           String qaOwner, Set<String> qaSet, String belongSystem, String belongBusiness,
                           List<ApplicationConfig> configs, String baseCodeBranch, String configFilePath,
                           String jarPath, String pomPath, String mvnCommand) {

        super(domainId, applicationName, applicationType, description, codeRepository, devOwner, devSet,
                qaOwner, qaSet, belongSystem, belongBusiness, configs);
        this.baseCodeBranch = baseCodeBranch;
        this.configFilePath = configFilePath;
        this.jarPath = jarPath;
        this.pomPath = pomPath;
        this.mvnCommand = mvnCommand;
    }

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
}
