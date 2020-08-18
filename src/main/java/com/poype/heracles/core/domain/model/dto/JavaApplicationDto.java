package com.poype.heracles.core.domain.model.dto;

import java.util.List;
import java.util.Map;

public class JavaApplicationDto {

    /**
     * 域名配置项名字
     */
    private List<String> hostConfigNames;

    /**
     * 各个环境的硬件级别, key是环境名称，value是硬件级别
     * @see com.poype.heracles.core.domain.model.enums.EnvironmentType
     * @see com.poype.heracles.core.domain.model.enums.HardwareLevelEnum
     */
    private Map<String, String> hardwareLevels;

    /**
     * 基准分支
     */
    private String baseCodeBranch;

    /**
     * 配置文件路径
     */
    private String configFilePath;

    /**
     * jar路径
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

    public JavaApplicationDto() {
    }

    public JavaApplicationDto(List<String> hostConfigNames, Map<String, String> hardwareLevels, String baseCodeBranch,
                              String configFilePath, String jarPath, String pomPath, String mvnCommand) {
        this.hostConfigNames = hostConfigNames;
        this.hardwareLevels = hardwareLevels;
        this.baseCodeBranch = baseCodeBranch;
        this.configFilePath = configFilePath;
        this.jarPath = jarPath;
        this.pomPath = pomPath;
        this.mvnCommand = mvnCommand;
    }

    @Override
    public String toString() {
        return "JavaApplicationDto{" +
                "hostConfigNames=" + hostConfigNames +
                ", hardwareLevels=" + hardwareLevels +
                ", baseCodeBranch='" + baseCodeBranch + '\'' +
                ", configFilePath='" + configFilePath + '\'' +
                ", jarPath='" + jarPath + '\'' +
                ", pomPath='" + pomPath + '\'' +
                ", mvnCommand='" + mvnCommand + '\'' +
                '}';
    }

    public List<String> getHostConfigNames() {
        return hostConfigNames;
    }

    public void setHostConfigNames(List<String> hostConfigNames) {
        this.hostConfigNames = hostConfigNames;
    }

    public Map<String, String> getHardwareLevels() {
        return hardwareLevels;
    }

    public void setHardwareLevels(Map<String, String> hardwareLevels) {
        this.hardwareLevels = hardwareLevels;
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
