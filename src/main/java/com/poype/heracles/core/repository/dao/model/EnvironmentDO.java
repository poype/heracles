package com.poype.heracles.core.repository.dao.model;

import java.util.Date;

public class EnvironmentDO {

    /**
     * 环境Id
     */
    private int envId;

    /**
     * 环境名称
     */
    private String envName;

    /**
     * 环境类型
     */
    private int envType;

    /**
     * 该环境下应用的默认CPU资源
     */
    private int defaultCpuOfApp;

    /**
     * 该环境下应用的默认内存资源
     */
    private int defaultMemoryOfApp;

    /**
     * 该环境下占用的总cpu资源
     */
    private int totalCpu;

    /**
     * 该环境下占用的总memory资源
     */
    private int totalMemory;

    /**
     * 环境状态
     */
    private int status;

    private Date createTime;

    private Date updateTime;

    public int getEnvId() {
        return envId;
    }

    public void setEnvId(int envId) {
        this.envId = envId;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public int getEnvType() {
        return envType;
    }

    public void setEnvType(int envType) {
        this.envType = envType;
    }

    public int getDefaultCpuOfApp() {
        return defaultCpuOfApp;
    }

    public void setDefaultCpuOfApp(int defaultCpuOfApp) {
        this.defaultCpuOfApp = defaultCpuOfApp;
    }

    public int getDefaultMemoryOfApp() {
        return defaultMemoryOfApp;
    }

    public void setDefaultMemoryOfApp(int defaultMemoryOfApp) {
        this.defaultMemoryOfApp = defaultMemoryOfApp;
    }

    public int getTotalCpu() {
        return totalCpu;
    }

    public void setTotalCpu(int totalCpu) {
        this.totalCpu = totalCpu;
    }

    public int getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(int totalMemory) {
        this.totalMemory = totalMemory;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
