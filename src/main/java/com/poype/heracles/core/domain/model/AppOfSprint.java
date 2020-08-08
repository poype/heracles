package com.poype.heracles.core.domain.model;

import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.enums.AppOfSprintStatus;

import java.util.List;
import java.util.Set;

/**
 * 关联在一个迭代中的应用
 */
public class AppOfSprint {

    /**
     * 应用与迭代关联记录Id
     */
    private String relationId;

    /**
     * 应用名字
     */
    private Application app;

    /**
     * 本次迭代中的代码分支
     */
    private String codeBranch;

    /**
     * 研发人员集合
     */
    private Set<String> devSet;

    /**
     * 测试人员集合
     */
    private Set<String> qaSet;

    /**
     * 本次迭代对应用host配置变化
     */
    private List<HostConfig> hostConfigs;

    /**
     * 应用状态
     */
    private AppOfSprintStatus status;

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public String getCodeBranch() {
        return codeBranch;
    }

    public void setCodeBranch(String codeBranch) {
        this.codeBranch = codeBranch;
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

    public List<HostConfig> getHostConfigs() {
        return hostConfigs;
    }

    public void setHostConfigs(List<HostConfig> hostConfigs) {
        this.hostConfigs = hostConfigs;
    }

    public AppOfSprintStatus getStatus() {
        return status;
    }

    public void setStatus(AppOfSprintStatus status) {
        this.status = status;
    }
}
