package com.poype.heracles.core.facade.result;

import java.util.List;

/**
 * 单个应用在版本中的状态
 */
public class AppOfSprintView {

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 代码仓库地址
     */
    private String codeRepos;

    /**
     * 代码分支
     */
    private String codeBranch;

    /**
     * 针对本次迭代的域名配置，只有后端才有该项配置
     */
    private List<String> hostConfigNames;

    /**
     * 应用开发状态
     */
    private String status;

    @Override
    public String toString() {
        return "AppOfSprintView{" +
                "appName='" + appName + '\'' +
                ", appType='" + appType + '\'' +
                ", codeRepos='" + codeRepos + '\'' +
                ", codeBranch='" + codeBranch + '\'' +
                ", hostConfigNames=" + hostConfigNames +
                ", status='" + status + '\'' +
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

    public String getCodeRepos() {
        return codeRepos;
    }

    public void setCodeRepos(String codeRepos) {
        this.codeRepos = codeRepos;
    }

    public String getCodeBranch() {
        return codeBranch;
    }

    public void setCodeBranch(String codeBranch) {
        this.codeBranch = codeBranch;
    }

    public List<String> getHostConfigNames() {
        return hostConfigNames;
    }

    public void setHostConfigNames(List<String> hostConfigNames) {
        this.hostConfigNames = hostConfigNames;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
